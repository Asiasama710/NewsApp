package com.asia.newsapp.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.asia.newsapp.R
import com.asia.newsapp.ui.theme.Theme

@Composable
fun CustomDialog(
    state: Boolean,
    message: String,
    positiveText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state,
        enter = fadeIn(animationSpec = tween(durationMillis = 100)),
        exit = fadeOut(animationSpec = tween(durationMillis = 100)),
    ) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(Theme.colors.background)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Theme.colors.contentTertiary,
                        style = MaterialTheme.typography.bodyMedium.copy(color =Theme.colors.contentTertiary ),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        TextButton(
                            modifier = Modifier
                                .width(144.dp)
                                .height(48.dp)
                                .padding(end = 8.dp),
                            onClick = onConfirm,
                            colors = ButtonDefaults.textButtonColors(Theme.colors.primary),
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Text(
                                text = positiveText,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                            )
                        }
                        TextButton(
                            modifier = Modifier
                                .width(144.dp)
                                .height(48.dp),
                            onClick = onCancel,
                            colors = ButtonDefaults.textButtonColors(containerColor = Color.Transparent),
                            shape = MaterialTheme.shapes.medium,
                        )
                        {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.bodySmall.copy(color = Theme.colors.primary)
                            )
                        }
                    }
                }
            }
        }
    }
}