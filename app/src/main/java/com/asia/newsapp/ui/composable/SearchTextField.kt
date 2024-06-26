package com.asia.newsapp.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.asia.newsapp.R
import com.asia.newsapp.ui.theme.Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                    containerColor = Theme.colors.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
            ),
            textStyle = Theme.typography.title,
            value = text,
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            onValueChange = onValueChanged,
            placeholder = {
                Text(
                        text = stringResource(R.string.search),
                        style = Theme.typography.title,
                        color = Theme.colors.onSurface
                )
            },
            leadingIcon = {
                    Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                            tint = Theme.colors.surface,
                            contentDescription = "searchIcon",
                    )
            },
    )
}