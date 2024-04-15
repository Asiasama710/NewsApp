package com.asia.newsapp.domain

import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.PaginationItems
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun searchForNews(query: String,pageNumber:Int,limit:Int): PaginationItems<Article>

    suspend fun updateBookmarkArticle(article: Article)

    suspend fun getBookmarkedArticles(): Flow<List<Article>>
}