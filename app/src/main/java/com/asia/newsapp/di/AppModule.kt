package com.asia.newsapp.di

import org.koin.dsl.module


fun appModule() = module {
    includes(
        LocalDatabaseModule,
        RepositoryModule,
        viewModelModule,
        NetworkModule,
        UseCaseModule
    )

}
