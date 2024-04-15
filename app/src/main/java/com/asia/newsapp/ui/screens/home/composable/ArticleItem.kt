package com.asia.newsapp.ui.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.asia.newsapp.R
import com.asia.newsapp.ui.screens.home.composable.modifier.noRippleEffect
import com.asia.newsapp.ui.theme.NewsAppTheme
import com.asia.newsapp.ui.theme.Theme



@Preview(showBackground = true)
@Composable
fun PreviewNewsItem() {
    NewsAppTheme {
        ArticleItem(
                title = "Traffic in Philippines' Capital City of Manila Worsens Despite Measures to Ease",
                description = "Traffic in Philippines' Capital City of Manila Worsens Despite Measures to Ease Congestion...",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                isBookmarked = false,
                author = "John Doe",
                publishedDate = " Oct 7, 2021 - 7:34 PM",
                onBookmarkedClicked = { /*TODO*/ },
                onItemClick = { /*TODO*/ }
        )
    }

}


@Composable
fun ArticleItem(
    title: String,
    description: String,
    imageUrl: String,
    isBookmarked: Boolean,
    author:String,
    publishedDate:String,
    onBookmarkedClicked: () -> Unit,
    onItemClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.padding(horizontal = 16.dp),
            colors =  CardDefaults.cardColors(
                    containerColor = Theme.colors.onPrimary,
                    contentColor = Theme.colors.onPrimary
            )
    ) {
        Column(
                modifier = Modifier.noRippleEffect { onItemClick() }
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black.copy(alpha = 0.3f))
                )
                Box(
                        Modifier
                            .fillMaxSize()
                            .padding(12.dp), contentAlignment = Alignment.BottomStart
                ) {
                    Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                                text = author,
                                style = Theme.typography.body,
                                color = Theme.colors.onPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                        )
                        Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                    painter =  painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = title,
                                    tint =  Theme.colors.onPrimary,
                                    modifier = Modifier.size(16.dp)
                            )
                            Text(
                                    text = publishedDate,
                                    style = Theme.typography.caption,
                                    color = Theme.colors.onPrimary,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                            )
                        }

                    }


                    IconButton(
                        onClick = onBookmarkedClicked,
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Theme.colors.onPrimary),
                            modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                                painter = if (isBookmarked) painterResource(id = R.drawable.ic_bookmarked_selected)
                                else painterResource(id = R.drawable.ic_bookmarked_unselected),
                                contentDescription = title,
                                tint = if (isBookmarked) Theme.colors.primary else Theme.colors.contentTertiary
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                        text = title,
                        style = Theme.typography.titleLarge,
                        color = Theme.colors.contentPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                )
                Text(
                        text = description,
                        style = Theme.typography.bodyMedium,
                        color = Theme.colors.contentTertiary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                )
                Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                            text = "Read more",
                            style = Theme.typography.caption,
                            color = Theme.colors.primary,
                            modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(
                            painter = painterResource(id = R.drawable.ic_read_more),
                            contentDescription = stringResource(id = R.string.read_more),
                            tint = Theme.colors.primary,
                            modifier = Modifier.size(18.dp)
                    )

                }
            }
        }

    }
}
