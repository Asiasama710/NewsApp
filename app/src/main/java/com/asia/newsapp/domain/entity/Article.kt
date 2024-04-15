package com.asia.newsapp.domain.entity


data class Article(
    val id: Long,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
    val isBookmarked: Boolean,
)

enum class ArticleType(val type: Int) {
    TOP_NEWS(0),
    LATEST_NEWS(1),
    SEARCH_NEWS(2);

    companion object {
        fun getArticleType(type: Int): ArticleType {
            entries.forEach {
                if (it.type == type) {
                    return it
                }
            }
            return TOP_NEWS
        }
    }
}