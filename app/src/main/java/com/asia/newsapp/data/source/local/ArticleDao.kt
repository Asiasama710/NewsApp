package com.asia.newsapp.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Query("SELECT id FROM Article_TABLE WHERE articleHeader = :title LIMIT 1")
    suspend fun getArticleIdByTitle(title: String): Long?


    @Query("SELECT * FROM Article_TABLE WHERE articleHeader LIKE '%' || :searchQuery || '%'")
    fun getSearchArticles(searchQuery: String): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM Article_TABLE WHERE isBookmarked = 1")
    fun getBookmarkedArticles(): Flow<List<ArticleEntity>>

    @Query("UPDATE Article_TABLE SET isBookmarked = NOT isBookmarked WHERE articleHeader = :articleTitle")
    suspend fun updateBookmarkStatus(articleTitle: String)
}