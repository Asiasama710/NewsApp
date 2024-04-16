package com.asia.newsapp.di

import com.asia.newsapp.domain.usecase.BookmarkedArticlesUseCase
import com.asia.newsapp.domain.usecase.SearchNewsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::SearchNewsUseCase)
    singleOf(::BookmarkedArticlesUseCase)
}