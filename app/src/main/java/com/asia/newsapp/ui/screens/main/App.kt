package com.asia.newsapp.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.asia.newsapp.ui.theme.NewsAppTheme
import com.asia.newsapp.ui.screens.main.navigation.Screen
import com.asia.newsapp.ui.screens.main.navigation.graph.RootNavGraph

@Composable
fun App() {
    NewsAppTheme {
            val navController = rememberNavController()
            RootNavGraph(navController = navController, startDestination = Screen.Main)
    }
}
