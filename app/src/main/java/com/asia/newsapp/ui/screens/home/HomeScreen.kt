package com.asia.newsapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.asia.newsapp.ui.composable.SearchTextField
import com.asia.newsapp.ui.theme.LocalNavigationProvider
import com.asia.newsapp.ui.theme.Theme
import com.asia.newsapp.ui.composable.ArticleItem
import com.asia.newsapp.ui.composable.PagingList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateTo: (HomeUiEffect) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    HomeScreenContent(
            state = state,
            listener = viewModel,
            onClickReadMore = { url ->
                navigateTo(HomeUiEffect.NavigateToWebView(url))
            }
    )
}

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    listener: HomeInteractionListener,
    onClickReadMore: (String) -> Unit
) {
    val news = state.news.collectAsLazyPagingItems()
    Log.e("TAG", "HomeScreenContent: ${news.itemSnapshotList}")
    Scaffold(
            modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
                modifier = Modifier
                    .padding(PaddingValues(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding()))
                    .background(Theme.colors.background)
        ) {
            SearchTextField(
                    text = state.keyword,
                    onValueChanged = listener::onSearchValueChanged,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            Divider(
                    modifier = Modifier
                        .shadow(
                                1.dp,
                                ambientColor = Color.LightGray,
                                spotColor = Color.LightGray
                        )
                        .fillMaxWidth(),
                    color = Color.Transparent
            )

                PagingList(
                        modifier = Modifier.fillMaxSize(),
                        data = news,
                        hasOptionalList = false,
                        optionalTopLList = emptyList(),
                        optionalHeaderTitle = "",
                        paddingValues = PaddingValues(0.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        optionalContent = {},
                        content = { item ->
                            item?.let {
                                ArticleItem(
                                        title = it.title,
                                        description = it.description,
                                        imageUrl = it.imageUrl,
                                        isBookmarked = it.isBookmarked,
                                        author = it.author,
                                        publishedDate = it.publishedAt,
                                        onBookmarkedClicked = { listener.onClickBookMark(it) },
                                        onItemClick = { onClickReadMore(it.url) }
                                )
                            }
                        }

                )
        }
    }
}