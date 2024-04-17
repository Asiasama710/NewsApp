package com.asia.newsapp.ui.screens.bookmarked

import com.asia.newsapp.ui.screens.home.ArticleUiState

data class BookmarkedUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val articles: List<ArticleUiState> = emptyList(),
    val showDialog: Boolean = false,
    val selectedArticle: ArticleUiState = ArticleUiState()
)