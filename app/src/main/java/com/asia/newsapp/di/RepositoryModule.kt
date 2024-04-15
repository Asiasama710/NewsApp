package com.asia.newsapp.di

import com.asia.newsapp.data.NewsRepositoryImp
import com.asia.newsapp.domain.NewsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf(::NewsRepositoryImp) { bind<NewsRepository>() }
}
