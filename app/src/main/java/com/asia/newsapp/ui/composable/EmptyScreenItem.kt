package com.asia.newsapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asia.newsapp.R
import com.asia.newsapp.ui.theme.Theme

@Composable
fun EmptyScreenItem(
    modifier: Modifier = Modifier,
    iconRes: Int = R.drawable.search_icon,
    title: String = stringResource(R.string.nothing_to_show),
    description: String = stringResource(R.string.empty_search_description)
) {
    Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(Theme.colors.border.copy(alpha = 0.5f))
                    .padding(24.dp)
        ) {
            Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(id = iconRes),
                    tint = Theme.colors.surface,
                    contentDescription = null
            )
        }
        Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    text = title,
                    style = Theme.typography.title,
                    color = Theme.colors.contentTertiary
            )

            Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = description,
                    style = Theme.typography.caption,
                    textAlign = TextAlign.Center,
                    color = Theme.colors.contentTertiary
            )
        }
    }
}