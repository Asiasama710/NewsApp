package com.asia.newsapp.ui.screens.bookmarked

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.asia.newsapp.ui.screens.home.HomeUiEffect
import com.asia.newsapp.ui.screens.home.composable.ArticleItem
import com.asia.newsapp.ui.theme.LocalNavigationProvider
import com.asia.newsapp.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkedScreen(
    navigateTo: (HomeUiEffect) -> Unit,
    viewModel: BookmarkedViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    BookmarkedScreenContent(
            state = state,
            listener = viewModel
    )
}

@Composable
fun BookmarkedScreenContent(
    state: BookmarkedUiState,
    listener: BookmarkedInteractionListener
) {

    Scaffold(
            modifier = Modifier.fillMaxSize()
    ) { padding ->
    LazyColumn(
            modifier = Modifier
                .padding(PaddingValues(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding()))
                .background(Theme.colors.background)
    ) {
        items(state.articles) { news ->
            ArticleItem(
                    title = news.title,
                    description = news.description,
                    imageUrl = news.imageUrl,
                    isBookmarked = news.isBookmarked,
                    author = news.author,
                    publishedDate = news.publishedAt,
                    onBookmarkedClicked = {  listener.onClickBookMark(news) },
                    onItemClick = {  }
            )
        }
    }}
}