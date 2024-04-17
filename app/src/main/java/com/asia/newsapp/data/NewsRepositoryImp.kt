package com.asia.newsapp.data

import com.asia.newsapp.data.source.local.ArticleDao
import com.asia.newsapp.data.source.remote.NewsService
import com.asia.newsapp.data.util.handleApiResponse
import com.asia.newsapp.data.util.toArticleEntity
import com.asia.newsapp.data.util.toEntity
import com.asia.newsapp.domain.NewsRepository
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.PaginationItems
import com.asia.newsapp.domain.util.NetworkException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class NewsRepositoryImp(
    private val service: NewsService,
    private val articleDao: ArticleDao,
) : NewsRepository {


    override suspend fun searchForNews(
        query: String,
        pageNumber: Int,
        limit: Int
    ): PaginationItems<Article> {
        try {
            val response = handleApiResponse(service.searchForNews(query, pageNumber, limit))
            val bookmarkedArticles =
                getBookmarkedArticles().firstOrNull()?.map { it.title } ?: emptyList()
            return response.articles.map { articleDto ->
                val isBookmarked = bookmarkedArticles.contains(articleDto.title)
                articleDto.toEntity().copy(isBookmarked = isBookmarked)
            }.let { articles ->
                PaginationItems(
                        items = articles,
                        page = pageNumber,
                        total = response.totalResults.toLong(),
                )
            }

        } catch (e: Exception) {
            throw NetworkException()
        }
    }

    override suspend fun getNews(sources: List<String>,page:Int): List<Article> {
         try {
            val response = handleApiResponse(service.getNews(sources.joinToString (","), page))
            return response.articles.map { it.toEntity() }

        }catch (e:Exception){
            throw NetworkException()
        }
    }

    override suspend fun saveArticles(articles: Article) {
        articleDao.insertArticle(articles.toArticleEntity())
    }

    override suspend fun removeArticle(articles: Article) {
        articleDao.deleteArticle(articles.toArticleEntity())
    }

    override suspend fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { it.toEntity() }
    }
}