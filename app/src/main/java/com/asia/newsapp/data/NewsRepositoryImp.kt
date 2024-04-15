package com.asia.newsapp.data

import android.util.Log
import com.asia.newsapp.data.source.local.ArticleDao
import com.asia.newsapp.data.source.local.toArticleEntity
import com.asia.newsapp.data.source.local.toEntity
import com.asia.newsapp.data.source.remote.NewsService
import com.asia.newsapp.data.source.remote.utilities.handleApiResponse
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
                getBookmarkedArticles().firstOrNull()?.filter { it.isBookmarked }?.map { it.title } ?: emptyList()

            Log.e("TAG", "bookmarkedArticles: $bookmarkedArticles")
            return response.articles.map { articleDto ->
                val isBookmarked = bookmarkedArticles.contains(articleDto.title)
                Log.e("TAG", "isBookmarked: $isBookmarked")
                articleDto.toEntity().copy(isBookmarked = isBookmarked)
            }.let { articles ->
                Log.e("TAG", "searchForNews: $articles")
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

//    override suspend fun searchForNews(query: String, pageNumber: Int, limit: Int): PaginationItems<Article> {
//        try {
//            val response = handleApiResponse(service.searchForNews(query, pageNumber, limit))
//            val articles = getBookmarkedArticles().map { it.map {article-> article.title } }
//             response.articles.forEach { articleDto->
//                 val d = articles.filterNot { it.contains(articleDto.title) }
//                 val f =articles.map { it.contains(articleDto.title) }
//            }
//
//            return response.articles.map { it.toEntity().copy(isBookmarked = true) }.let {articles->
//                PaginationItems(
//                        items = articles,
//                        page = pageNumber,
//                        total = response.totalResults.toLong(),
//                )
//            }
//
//        } catch (e: Exception) {
//            throw NetworkException()
//        }
//    }


    override suspend fun updateBookmarkArticle(article: Article) {
        Log.e("TAG", "updateBookmarkArticle: $article")
        val articleId = articleDao.getArticleIdByTitle(article.title)
        if (articleId != null) {
            // Article exists in the database, update it
            articleDao.updateBookmarkStatus(article.title)
            Log.e("TAG", "updateBookmarkStatus: ")
        } else {
            Log.e("TAG", "insertArticle " )
            // Article does not exist in the database, insert it
            articleDao.insertArticle(article.toArticleEntity())
        }
    }

    override suspend fun getBookmarkedArticles(): Flow<List<Article>> {
        return articleDao.getBookmarkedArticles().map { it.toEntity() }
    }
}