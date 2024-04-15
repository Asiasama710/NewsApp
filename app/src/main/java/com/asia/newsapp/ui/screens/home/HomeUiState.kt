package com.asia.newsapp.ui.screens.home

import androidx.paging.PagingData
import androidx.paging.map
import com.asia.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val keyword: String = "",
    val news: Flow<PagingData<ArticleUiState>> = emptyFlow(),
)

data class ArticleUiState(
    val id: Long,
    val title: String,
    val publishedAt: String,
    val author: String,
    val url: String,
    val description: String,
    val imageUrl: String,
    val isBookmarked: Boolean,
)

fun Article.toUIState(): ArticleUiState {
    return ArticleUiState(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        isBookmarked = isBookmarked,
        publishedAt = publishedAt,
        author = author,
        url = url
    )
}

fun ArticleUiState.toEntity(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        isBookmarked = isBookmarked,
        publishedAt = publishedAt,
        author = author,
        url = url,
        content = ""
    )
}

fun  List<Article>.toUiState(): List<ArticleUiState> {
    return this.map { it.toUIState() }
}
fun Flow<PagingData<Article>>.toUIState(): Flow<PagingData<ArticleUiState>> {
    return this.map { pagingData -> pagingData.map {
        it.toUIState()
    } }
}
