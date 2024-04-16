package com.asia.newsapp.di

import com.asia.newsapp.data.source.remote.NewsService
import com.asia.newsapp.data.source.remote.utilities.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    fun provideOkHttpClient(authInterceptor: AuthInterceptor,logging:HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).addInterceptor(logging).build()
    }

    fun provideNewsApi(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get(),get()) }
    factory { provideNewsApi(get()) }
    factory { provideHttpLoggingInterceptor() }
    single { provideRetrofit(get()) }

}
