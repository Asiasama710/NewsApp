package com.asia.newsapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.usecase.BookmarkedArticlesUseCase
import com.asia.newsapp.ui.screens.base.BaseViewModel
import com.asia.newsapp.ui.util.pagingSource.SearchNewsDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class HomeViewModel(
    private val searchNewsDataSource: SearchNewsDataSource,
    private val updateArticle: BookmarkedArticlesUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    private var searchJob: Job? = null
    private val config = PagingConfig(pageSize = 10, prefetchDistance = 5, enablePlaceholders = false)

     private fun searchForNews(query: String): Flow<PagingData<Article>> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config = config, pagingSourceFactory = { searchNewsDataSource }).flow
    }

    override fun onSearchValueChanged(query: String) {
        updateState { it.copy(keyword = query) }
        if (query.isEmpty()) return
        onSearch()
    }
    override fun onClickBookMark(article: ArticleUiState) {
        Log.e("TAG", "onClickBookMark: $article")
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
                news = state.value.news.map { articles -> articles.map {articleState->
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
        updateState { it.copy(news = result.toUIState(), isLoading = false) }
        Log.e("TAG", "onSuccess:${ result.toUIState()}")
    }


    private fun onError() {
        updateState {
            it.copy(
                    isLoading = false,
                    isError = true,
            )
        }
    }

}