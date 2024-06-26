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
    val articles: Flow<PagingData<ArticleUiState>> = emptyFlow(),
    val news:List<ArticleUiState> = emptyList(),
    val showDialog: Boolean = false,
    val selectedArticle: ArticleUiState = ArticleUiState()
){
    enum class SourceName(val value: String) {
        BBC("bbc-news"),
        ABC("abc-news"),
        ALJAZEERA("al-jazeera-english")
    }
}

data class ArticleUiState(
    val title: String="",
    val publishedAt: String="",
    val author: String="",
    val url: String="",
    val description: String="",
    val imageUrl: String="",
    val isBookmarked: Boolean=false,
)

fun Article.toUIState(): ArticleUiState {
    return ArticleUiState(
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
            author = author,
            title = title,
            description = description,
            url = url,
            imageUrl = imageUrl,
            publishedAt = publishedAt,
            content = "",
            isBookmarked = isBookmarked
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
