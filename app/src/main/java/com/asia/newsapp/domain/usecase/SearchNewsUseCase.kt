package com.asia.newsapp.domain.usecase

import com.asia.newsapp.domain.NewsRepository
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.PaginationItems

class SearchNewsUseCase (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(searchQuery: String,pageNumber:Int,limit:Int): PaginationItems<Article> {
        return newsRepository.searchForNews(searchQuery,pageNumber,limit)
    }
}