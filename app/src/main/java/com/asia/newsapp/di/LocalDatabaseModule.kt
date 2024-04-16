package com.asia.newsapp.di

import androidx.room.Room
import com.asia.newsapp.data.source.local.NewsDatabase
import com.asia.newsapp.ui.util.pagingSource.SearchNewsDataSource

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), NewsDatabase::class.java, "NewsDatabase")
            .build()
    }

    single {
        val database = get<NewsDatabase>()
        database.articleDao()
    }
    singleOf(::SearchNewsDataSource)

}
