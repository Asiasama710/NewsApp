package com.asia.newsapp.ui.screens.webview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewScreen(
    url: String
) {
        Scaffold(
                modifier = Modifier.fillMaxSize()
        ) { padding ->
            AndroidView(
                    modifier = Modifier.padding(
                            top = padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding()
                    ),
                    factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            loadUrl(url)
                        }
                    }, update = {
                it.loadUrl(url)
            })

        }
}