package com.asia.newsapp.ui.screens.home

interface HomeInteractionListener {
    fun onSearchValueChanged(query: String)
    fun onClickBookMark(article: ArticleUiState)

}