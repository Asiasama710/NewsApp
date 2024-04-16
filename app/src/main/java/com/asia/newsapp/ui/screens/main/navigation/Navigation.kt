package com.asia.newsapp.ui.screens.main.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asia.newsapp.ui.screens.bookmarked.BookmarkedScreen
import com.asia.newsapp.ui.screens.bookmarked.BookmarkedUiEffect
import com.asia.newsapp.ui.screens.home.HomeScreen
import com.asia.newsapp.ui.screens.home.HomeUiEffect
import com.asia.newsapp.ui.screens.webview.WebViewScreen
import com.asia.newsapp.ui.screens.main.navigation.ext.navigateTo
import com.asia.newsapp.ui.theme.Theme
import com.asia.newsapp.ui.util.setStatusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


fun NavGraphBuilder.mainNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(route = Screen.Main.route) {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomBar: @Composable () -> Unit = {
            BottomNavigation(
                    screens = listOf(
                            Screen.Home,
                            Screen.Bookmarked
                    ),
                    onNavigateTo = navController::navigateTo,
                    currentDestination = navBackStackEntry?.destination
            )
        }

        Scaffold(
                bottomBar = bottomBar,
                contentWindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0)
        ) { paddingValues ->

            val systemUIController = rememberSystemUiController()
            val color = Theme.colors.background
            LaunchedEffect(true) {
                setStatusBarColor(
                        systemUIController = systemUIController,
                        statusBarColor = color,
                )
            }

            Surface(
                    color = if (color == Color.Unspecified) MaterialTheme.colorScheme.surface else color,
                    modifier = Modifier.fillMaxSize(),
            ) {
                CompositionLocalProvider(
                        LocalAbsoluteTonalElevation provides 0.dp
                ) {
                    NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route,
                    ) {
                        homeScreen(onNavigateToRoot)
                        bookmarkedScreen(onNavigateToRoot)
                    }
                }
            }
        }
    }

}

fun NavGraphBuilder.homeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(route = Screen.Home.route) {
        HomeScreen(
                navigateTo = { navigate ->
                    when (navigate) {
                        is HomeUiEffect.NavigateToWebView -> {
                            Screen.WebView.args = bundleOf(Pair("url", navigate.url))
                            Screen.WebView.withClearBackStack().also(onNavigateTo)
                        }
                    }
                },
        )
    }
}

fun NavGraphBuilder.bookmarkedScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
            route = Screen.Bookmarked.route
    ) {
        BookmarkedScreen(
                navigateTo = { navigate ->
                    when (navigate) {
                        is BookmarkedUiEffect.NavigateToWebView -> {
                            Screen.WebView.args = bundleOf(Pair("url", navigate.url))
                            Screen.WebView.withClearBackStack().also(onNavigateTo)
                        }
                    }
                },
        )
    }
}

fun NavGraphBuilder.webViewScreen() {
    composable(
            route = Screen.WebView.route
    ) {
        val url = Screen.WebView.args?.getString("url").toString()
        WebViewScreen(url)
    }
}