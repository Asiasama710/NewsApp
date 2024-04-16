package com.asia.newsapp.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asia.newsapp.data.source.remote.model.ArticleDto
import com.asia.newsapp.domain.entity.Article

@Entity("Article_TABLE")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false) val articleHeader: String,
    val articleDescription: String,
    val articleContent: String,
    val articleImageUrl: String,
    val articleDate: String,
    val articleUrl: String,
    val articleAuthor: String ,
    val isBookmarked: Boolean = true,
)

