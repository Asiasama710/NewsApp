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
import com.asia.newsapp.domain.usecase.GetNewsUseCase
import com.asia.newsapp.ui.screens.base.BaseViewModel
import com.asia.newsapp.ui.util.pagingSource.SearchNewsDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


class HomeViewModel(
    private val searchNewsDataSource: SearchNewsDataSource,
    private val updateArticle: BookmarkedArticlesUseCase,
    private val getNews: GetNewsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    private var searchJob: Job? = null
    init {
        getNews()
    }

    private fun getNews() {
        updateState { it.copy(isLoading = true,isError = false) }
        tryToExecute(
                { getNews( HomeUiState.SourceName.entries.map { it.value }) },
                { onGetNewsSuccess(it) },
                ::onError
        )
    }

    private fun onGetNewsSuccess(news: List<Article>) {
        updateState { it.copy(news = news.toUiState(), isLoading = false) }
    }

    override fun onRetryNews() {
        getNews()
    }

     private fun searchForNews(query: String): Flow<PagingData<Article>> {
        searchNewsDataSource.setSearchText(query)
        return Pager(config =  PagingConfig(pageSize = 5), pagingSourceFactory = { searchNewsDataSource }).flow
    }

    override fun onSearchValueChanged(query: String) {
        updateState { it.copy(keyword = query, isLoading = true, isError = false) }
        if (query.isEmpty()) {
            updateState { it.copy(articles = emptyFlow(), isLoading = false) }
            return
        }
        launchSearchJob()
    }

    private fun onSearch() {
        updateState { it.copy(isLoading = true, isError = false) }
        tryToExecute(
                { searchForNews(state.value.keyword.trim()).distinctUntilChanged() },
                { onSuccess(it) },
                ::onError
        )
    }

    private fun launchSearchJob() {
        searchJob?.cancel()
        searchJob = launchDelayed(300L) { this@HomeViewModel.onSearch() }
    }

    private fun onSuccess(result: Flow<PagingData<Article>>) {
        updateState { it.copy(articles =  result.toUIState()) }
        updateState { it.copy( isLoading = false) }
    }

    private fun deleteArticle(article: ArticleUiState) {
        tryToExecute(
                {  updateArticle.removeArticle(article.toEntity()) },
                { updateBookmarkStatus(article,false) },
                ::onError
        )
    }


    override fun onClickBookMark(article: ArticleUiState) {
        if (article.isBookmarked) {
            updateState { it.copy(showDialog = true, selectedArticle = article) }
        } else {
            saveArticle(article)
        }
    }

    override fun onClickDeleteFromBookMarked(article: ArticleUiState) {
        updateState { it.copy(showDialog = false) }
        deleteArticle(article)
    }

    override fun dismissDialog() {
        updateState { it.copy(showDialog = false) }
    }


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










    private fun onError() {
        updateState { it.copy(isLoading = false, isError = true, articles = emptyFlow()) }
    }


}