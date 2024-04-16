package com.asia.newsapp.ui.composable

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.asia.newsapp.ui.theme.Theme

@Composable
fun <T : Any> PagingList(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<T>,
    hasOptionalList: Boolean = false,
    optionalTopLList: List<T> = emptyList(),
    optionalHeaderTitle: String = "",
    paddingValues: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    optionalContent: @Composable (T) -> Unit = {},
    content: @Composable (T?) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddingValues
    ) {

        item {
            AnimatedVisibility(hasOptionalList) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Theme.colors.primary)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = optionalHeaderTitle,
                        style = Theme.typography.titleMedium,
                        color = Theme.colors.onPrimary
                    )
                }
            }
        }


        items(data.itemCount) { index ->
            val item = data[index]
            content(item)
        }

        data.loadState.apply {
            when {
                refresh is LoadState.NotLoading && data.itemCount < 1 -> {
                    Log.e("TAG", "PagingList:  LoadState.NotLoading && data.itemCount < 1")


                    items(optionalTopLList) { item ->
                        optionalContent(item)
                    }
                }

                refresh is LoadState.Loading -> {
                    Log.e("TAG", "PagingList: loading state refresh")
                    item {

                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            EmptyScreenItem()
                        }
                    }
                }

                append is LoadState.Loading -> {
                    Log.e("TAG", "PagingList: loading state append")
                    item {
                        Loading()
                    }
                }

                refresh is LoadState.Error -> {
                    item {
                        ErrorView(
                            message = "No Internet Connection",
                            onClickRetry = { data.retry() },
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }

                append is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = "No Internet Connection",
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
            Text(text = "Try again")
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
            .padding(16.dp)
            .onPlaced { cor ->

            },
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
                title = "Try again",
                onClick = onClickRetry,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}