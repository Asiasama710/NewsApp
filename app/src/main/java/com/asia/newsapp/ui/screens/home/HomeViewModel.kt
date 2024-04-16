package com.asia.newsapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.usecase.BookmarkedArticlesUseCase
import com.asia.newsapp.ui.screens.base.BaseViewModel
import com.asia.newsapp.ui.util.pagingSource.SearchNewsDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(
    private val searchNewsDataSource: SearchNewsDataSource,
    private val updateArticle: BookmarkedArticlesUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    private var searchJob: Job? = null

     private fun searchForNews(query: String): Flow<PagingData<Article>> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config =  PagingConfig(pageSize = 5), pagingSourceFactory = { searchNewsDataSource }).flow
    }

    override fun onSearchValueChanged(query: String) {
        updateState { it.copy(keyword = query) }
        if (query.isEmpty()) return
        onSearch()
    }
    override fun onClickBookMark(article: ArticleUiState) {
        if (article.isBookmarked){
            deleteArticle(article)
        }else{
            saveArticle(article)
        }
    }

    //save in database and change state to true
    private fun saveArticle(article: ArticleUiState) {
        tryToExecute(
                {  updateArticle.saveArticle(article.toEntity()) },
                { updateBookmarkStatus(article,true) },
                ::onError
        )
    }

    private fun updateBookmarkStatus(article: ArticleUiState,isBookmarked: Boolean) {
        updateState { it.copy(
                articles = state.value.articles.map { articles -> articles.map { articleState->
                    if (articleState.title == article.title) {
                        articleState.copy(isBookmarked = isBookmarked)
                    } else {
                        articleState
                    }
                } }
        ) }
    }

    private fun deleteArticle(article: ArticleUiState) {
        tryToExecute(
                {  updateArticle.removeArticle(article.toEntity()) },
                { updateBookmarkStatus(article,false) },
                ::onError
        )
    }

    private fun onSearch() {
        updateState { it.copy(isLoading = true, isError = false) }
        searchJob?.cancel()
        searchJob = tryToExecute(
                {
                    delay(1500)
                    searchForNews(state.value.keyword.trim())
                },
                { onSuccess(it) },
                ::onError
        )
    }


    private fun onSuccess(result: Flow<PagingData<Article>>) {
        updateState { it.copy(articles = result.toUIState(), isLoading = false) }
    }


    private fun onError() {
        updateState { it.copy(isLoading = false, isError = true,) }
    }

}