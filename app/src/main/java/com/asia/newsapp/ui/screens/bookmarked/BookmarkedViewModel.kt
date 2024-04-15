package com.asia.newsapp.ui.screens.bookmarked

import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.usecase.BookmarkedArticlesUseCase
import com.asia.newsapp.ui.screens.base.BaseViewModel
import com.asia.newsapp.ui.screens.home.ArticleUiState
import com.asia.newsapp.ui.screens.home.toEntity
import com.asia.newsapp.ui.screens.home.toUiState


class BookmarkedViewModel(
    private val getBookmarkedNews: BookmarkedArticlesUseCase
) : BaseViewModel<BookmarkedUiState, BookmarkedUiEffect>(BookmarkedUiState()),
    BookmarkedInteractionListener {

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
                function = getBookmarkedNews::getBookmarkedArticles,
                onSuccess = ::onGetArticlesSuccess,
                onError = ::onError
        )
    }

    private fun onGetArticlesSuccess(articles: List<Article>) {
        updateState { it.copy(isLoading = false, articles = articles.toUiState()) }
    }

    private fun updateBookmarkStatus(article: ArticleUiState) {
        tryToExecute(
                function = { getBookmarkedNews::updateBookmarkArticle.invoke(article.toEntity())},
                onSuccess = { onUpdateBookmarkStatusSuccess() },
                onError = ::onError
        )
    }

    private fun onUpdateBookmarkStatusSuccess() {
    }

    private fun onError() {
        updateState { it.copy(isLoading = false,isError = true) }
    }

    override fun onClickBookMark(article: ArticleUiState) {
        updateBookmarkStatus(article)
    }

}