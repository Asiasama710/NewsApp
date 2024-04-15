package com.asia.newsapp.ui.screens.bookmarked

import com.asia.newsapp.ui.screens.home.ArticleUiState

interface BookmarkedInteractionListener {
    fun onClickBookMark(article: ArticleUiState)
}