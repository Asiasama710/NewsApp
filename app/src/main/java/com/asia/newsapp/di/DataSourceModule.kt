package com.asia.newsapp.di

import com.asia.newsapp.ui.util.pagingSource.SearchNewsDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::SearchNewsDataSource)
}