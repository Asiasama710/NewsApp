package com.asia.newsapp.ui.screens.bookmarked

import android.util.Log
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.usecase.BookmarkedArticlesUseCase
import com.asia.newsapp.ui.screens.base.BaseViewModel
import com.asia.newsapp.ui.screens.home.ArticleUiState
import com.asia.newsapp.ui.screens.home.toEntity
import com.asia.newsapp.ui.screens.home.toUiState


class BookmarkedViewModel(
    private val bookmarkedNews: BookmarkedArticlesUseCase
) : BaseViewModel<BookmarkedUiState, BookmarkedUiEffect>(BookmarkedUiState()),
    BookmarkedInteractionListener {

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
                function = bookmarkedNews::getBookmarkedArticles,
                onSuccess = ::onGetArticlesSuccess,
                onError = ::onError
        )
    }

    private fun onGetArticlesSuccess(articles: List<Article>) {
        Log.e("TAG", "onGetArticlesSuccess: $articles")
        updateState { it.copy(isLoading = false, articles = articles.toUiState()) }
    }

    override fun onClickBookMark(article: ArticleUiState) {
        Log.e("TAG", "onClickBookMark: $article")
        if (article.isBookmarked) {
            deleteArticle(article)
        } else {
            saveArticle(article)
        }
    }

    //save in database and change state to true
    private fun saveArticle(article: ArticleUiState) {
        tryToExecute(
                { bookmarkedNews.saveArticle(article.toEntity()) },
                { updateBookmarkStatus(article, true) },
                ::onError
        )
    }

    private fun deleteArticle(article: ArticleUiState) {
        tryToExecute(
                { bookmarkedNews.removeArticle(article.toEntity()) },
                { updateBookmarkStatus(article, false) },
                ::onError
        )
    }

    private fun updateBookmarkStatus(article: ArticleUiState, isBookmarked: Boolean) {
        updateState {
            it.copy(
                    articles = state.value.articles.map { articleState ->
                        if (articleState.title == article.title) {
                            articleState.copy(isBookmarked = isBookmarked)
                        } else {
                            articleState
                        }
                    }
            )
        }
    }

    private fun onError() {
        updateState { it.copy(isLoading = false, isError = true) }
    }

}