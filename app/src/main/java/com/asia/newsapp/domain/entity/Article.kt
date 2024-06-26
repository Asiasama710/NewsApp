package com.asia.newsapp.domain.entity


data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isBookmarked: Boolean,
)