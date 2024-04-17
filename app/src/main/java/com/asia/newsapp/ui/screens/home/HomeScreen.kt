package com.asia.newsapp.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.asia.newsapp.R
import com.asia.newsapp.ui.composable.ArticleItem
import com.asia.newsapp.ui.composable.CustomDialog
import com.asia.newsapp.ui.composable.ErrorView
import com.asia.newsapp.ui.composable.Loading
import com.asia.newsapp.ui.composable.PagingList
import com.asia.newsapp.ui.composable.SearchTextField
import com.asia.newsapp.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateTo: (HomeUiEffect) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenContent(
            state = state,
            listener = viewModel,
            onClickReadMore = { url ->
                navigateTo(HomeUiEffect.NavigateToWebView(url))
            }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    state: HomeUiState,
    listener: HomeInteractionListener,
    onClickReadMore: (String) -> Unit
) {
    CustomDialog(
            state = state.showDialog,
            message = stringResource(id = R.string.are_u_sure_to_delete_from_book_marck),
            positiveText = stringResource(id = R.string.yes),
            onConfirm = { listener.onClickDeleteFromBookMarked(state.selectedArticle) },
            onCancel = { listener.dismissDialog() },
            onDismissRequest = { listener.dismissDialog() }
    )
    Column(
            modifier = Modifier
                .statusBarsPadding()
                .background(Theme.colors.background)
    ) {
        SearchTextField(
                text = state.keyword,
                onValueChanged = listener::onSearchValueChanged,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Divider(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, ambientColor = Color.LightGray, spotColor = Color.LightGray)
        )


        val articles = state.articles.collectAsLazyPagingItems()


        if (state.isError) {
            Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
            ) {
                ErrorView(onClickRetry = { listener.onRetryNews() })
            }
        }
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                Loading()
            }
        } else {
            if (articles.itemSnapshotList.isEmpty() && state.keyword.isBlank()) {
                LazyColumn(
                        modifier = Modifier.background(Theme.colors.background),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp)
                ) {
                    items(state.news) {
                        ArticleItem(
                                modifier = Modifier.animateItemPlacement(),
                                title = it.title,
                                description = it.description,
                                imageUrl = it.imageUrl,
                                isBookmarked = it.isBookmarked,
                                author = it.author,
                                publishedDate = it.publishedAt,
                                onItemClick = { onClickReadMore(it.url) },
                                isBookmarkedShow = false
                        )
                    }
                }

            }
            PagingList(
                    modifier = Modifier.fillMaxSize(),
                    data = articles,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        items(articles.itemSnapshotList.items) {
                            ArticleItem(
                                    modifier = Modifier.animateItemPlacement(),
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