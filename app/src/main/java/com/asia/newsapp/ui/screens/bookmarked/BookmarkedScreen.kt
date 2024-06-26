package com.asia.newsapp.ui.screens.bookmarked


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asia.newsapp.R
import com.asia.newsapp.ui.composable.ArticleItem
import com.asia.newsapp.ui.composable.CustomDialog
import com.asia.newsapp.ui.composable.EmptyScreenItem
import com.asia.newsapp.ui.composable.ErrorView
import com.asia.newsapp.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkedScreen(
    navigateTo: (BookmarkedUiEffect) -> Unit,
    viewModel: BookmarkedViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    BookmarkedScreenContent(
            state = state,
            listener = viewModel,
            onClickReadMore = { url ->
                navigateTo(BookmarkedUiEffect.NavigateToWebView(url))
            }
    )


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkedScreenContent(
    state: BookmarkedUiState,
    listener: BookmarkedInteractionListener,
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
        LazyColumn(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()

                    .background(Theme.colors.background),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp)
        ) {

            item {
                Text(

                        text = stringResource(R.string.bookmarked),
                        style = Theme.typography.headline,
                        color = Theme.colors.contentPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                )
                Divider(
                        color = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                    1.dp,
                                    ambientColor = Color.LightGray,
                                    spotColor = Color.LightGray
                            )
                )
            }
            if (state.articles.isEmpty()) {
                item {
                    Box( modifier = Modifier.fillParentMaxSize(),contentAlignment = Alignment.Center
                    ) {
                        EmptyScreenItem(modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize())
                    }
                }
            }else{
                items(state.articles, key = { article -> article.title }) { news ->
                    ArticleItem(
                            modifier = Modifier.animateItemPlacement(),
                            title = news.title,
                            description = news.description,
                            imageUrl = news.imageUrl,
                            isBookmarked = news.isBookmarked,
                            author = news.author,
                            publishedDate = news.publishedAt,
                            onBookmarkedClicked = { listener.onClickBookMark(news) },
                            onItemClick = { onClickReadMore(news.url) }
                    )
                }
            }

    }
}