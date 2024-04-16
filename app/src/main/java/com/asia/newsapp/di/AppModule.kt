package com.asia.newsapp.di

import org.koin.dsl.module


fun appModule() = module {
    includes(
        localDatabaseModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        networkModule,
        useCaseModule
    )

}
