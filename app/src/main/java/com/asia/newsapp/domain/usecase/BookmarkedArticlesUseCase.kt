package com.asia.newsapp.domain.usecase

import com.asia.newsapp.domain.NewsRepository
import com.asia.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class BookmarkedArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun getBookmarkedArticles(): Flow<List<Article>> {
        return newsRepository.getBookmarkedArticles().map { articles ->
            articles.sortedByDescending { articleEntity ->
                    articleEntity.publishedAt
                }
        }
    }

    suspend fun saveArticle(article: Article) {
        newsRepository.saveArticles(article)
    }

    suspend fun removeArticle(article: Article) {
        newsRepository.removeArticle(article)
    }
}
