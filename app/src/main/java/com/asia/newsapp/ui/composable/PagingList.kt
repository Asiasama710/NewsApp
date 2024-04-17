package com.asia.newsapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.asia.newsapp.R
import com.asia.newsapp.ui.theme.Theme

@Composable
fun <T : Any> PagingList(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<T>,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    content: LazyListScope.() -> Unit,
) {

    LazyColumn(
        modifier = modifier.background(Theme.colors.background),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddingValues
    ) {

        content()

        item {
            data.loadState.apply {
                when {

                    refresh is LoadState.NotLoading && data.itemSnapshotList.isEmpty() -> {

                        Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                        ) {
                            Text(
                                    text = "No Results Found",
                                    modifier = Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center
                            )
                        }
                    }
                    append is LoadState.Loading -> {
                        Loading()
                    }

                    refresh is LoadState.Error -> {

                        Box(
                                modifier = Modifier.fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                        ) {
                            ErrorView(
                                    message = stringResource(R.string.no_internet_connection),
                                    onClickRetry = { data.retry() },
                            )
                        }
                    }

                    append is LoadState.Error -> {
                        ErrorItem(
                                message = stringResource(R.string.no_internet_connection),
                                onClickRetry = { data.retry() },
                        )
                    }

                }
            }
        }



    }
}

@Composable
private fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = Theme.typography.body,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(R.string.try_again),style =Theme.typography.caption, color = Theme.colors.contentPrimary)
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = Theme.typography.body,
            color = Color.Red
        )
        OutlinedButton(
                title = stringResource(R.string.try_again),
                onClick = onClickRetry,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}