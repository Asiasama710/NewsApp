package com.asia.newsapp.ui.screens.home

sealed interface HomeUiEffect {
    data class NavigateToWebView(val url: String) : HomeUiEffect

}