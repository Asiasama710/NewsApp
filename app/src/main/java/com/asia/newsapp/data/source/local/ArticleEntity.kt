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
    val isBookmarked: Boolean = true,
)

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
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
        author = "",
        title = articleHeader,
        description = articleDescription,
        content = articleContent,
        publishedAt = articleDate,
        imageUrl = articleImageUrl,
        url = "",
        isBookmarked = true,
    )
}


fun List<ArticleEntity>.toEntity() = map { it.toEntity() }