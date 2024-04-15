package com.asia.newsapp.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asia.newsapp.data.source.remote.model.ArticleDto
import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.ArticleType

@Entity("Article_TABLE")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val articleHeader: String,
    val articleDescription: String,
    val articleContent: String,
    val articleImageUrl: String,
    val articleDate: String,
    val isBookmarked: Boolean,
)

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
            id = id,
            articleHeader = title,
            articleDescription = description,
            articleContent = content,
            articleDate = publishedAt,
            articleImageUrl = imageUrl,
            isBookmarked = isBookmarked,
    )
}
fun ArticleDto.toEntity(): Article {
    return Article(
        id = 0,
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        content = content ?: "",
        publishedAt = publishedAt,
        imageUrl = urlToImage ?: "",
        url = url ?: "",
        isBookmarked = false,
    )
}
fun ArticleEntity.toEntity(): Article {
    return Article(
        id = id,
        author = "",
        title = articleHeader,
        description = articleDescription,
        content = articleContent,
        publishedAt = articleDate,
        imageUrl = articleImageUrl,
        url = "",
        isBookmarked = isBookmarked,
    )
}

fun ArticleDto.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleHeader = title ?: "",
        articleDescription = description ?: "",
        articleContent = content ?: "",
        articleImageUrl = urlToImage ?: "",
        articleDate = publishedAt,
        isBookmarked = false,
    )
}

fun List<ArticleEntity>.toEntity() = map { it.toEntity() }