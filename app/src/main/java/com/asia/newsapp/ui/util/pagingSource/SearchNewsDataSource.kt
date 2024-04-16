package com.asia.newsapp.ui.util.pagingSource

import com.asia.newsapp.domain.entity.Article
import com.asia.newsapp.domain.entity.PaginationItems
import com.asia.newsapp.domain.usecase.SearchNewsUseCase
import kotlin.properties.Delegates

class SearchNewsDataSource(
    private val searchNewsUseCase: SearchNewsUseCase
) : BasePagingSource<Article>() {

    private var newsSearchText by Delegates.notNull<String>()

    fun setSearchText(searchText: String) {
        newsSearchText = searchText
    }

    override suspend fun fetchData(page: Int, limit: Int): PaginationItems<Article> {
        return searchNewsUseCase(newsSearchText, page, limit)
    }
}