package com.asia.newsapp.data.util

import com.asia.newsapp.data.source.local.ArticleEntity
import com.asia.newsapp.data.source.remote.model.ArticleDto
import com.asia.newsapp.domain.entity.Article

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
            articleHeader = title,
            articleDescription = description,
            articleContent = content,
            articleDate = publishedAt,
            articleImageUrl = imageUrl,
            isBookmarked = isBookmarked,
            articleUrl = url,
            articleAuthor = author,
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
            author = articleAuthor,
            title = articleHeader,
            description = articleDescription,
            content = articleContent,
            publishedAt = articleDate,
            imageUrl = articleImageUrl,
            url = articleUrl,
            isBookmarked = true,
    )
}


fun List<ArticleEntity>.toEntity() = map { it.toEntity() }