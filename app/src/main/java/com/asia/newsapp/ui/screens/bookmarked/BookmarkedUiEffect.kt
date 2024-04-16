package com.asia.newsapp.ui.screens.bookmarked

sealed interface BookmarkedUiEffect {
    data class NavigateToWebView(val url: String) : BookmarkedUiEffect

}