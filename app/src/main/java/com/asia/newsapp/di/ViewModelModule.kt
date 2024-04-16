package com.asia.newsapp.di

import com.asia.newsapp.ui.screens.bookmarked.BookmarkedViewModel
import com.asia.newsapp.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::BookmarkedViewModel)
}
