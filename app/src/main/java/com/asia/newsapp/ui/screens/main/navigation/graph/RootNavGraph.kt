package com.asia.newsapp.ui.screens.main.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.asia.newsapp.ui.screens.main.navigation.Screen
import com.asia.newsapp.ui.screens.main.navigation.ext.navigateTo
import com.asia.newsapp.ui.screens.main.navigation.mainNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen
) {
    NavHost(
            navController = navController,
            route = "root_host",
            startDestination = startDestination.route,
            modifier = modifier,
    ) {
        mainNavGraph(onNavigateToRoot = navController::navigateTo)
    }
}
