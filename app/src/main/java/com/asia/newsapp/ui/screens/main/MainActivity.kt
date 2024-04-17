package com.asia.newsapp.ui.screens.main

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.asia.newsapp.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Theme.colors.background,
                    content = {
                        App()
                    }
            )
        }
    }
}
