package com.asia.newsapp.domain.usecase

import com.asia.newsapp.domain.NewsRepository
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.PaginationItems

class GetNewsUseCase (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(sources: List<String>): List<Article> {
        return newsRepository.getNews(sources,1)
    }
}