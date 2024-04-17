package com.asia.newsapp.ui.screens.home

interface HomeInteractionListener {
    fun onSearchValueChanged(query: String)
    fun onRetryNews()
    fun onClickBookMark(article: ArticleUiState)
    fun onClickDeleteFromBookMarked(article: ArticleUiState)
    fun dismissDialog()

}