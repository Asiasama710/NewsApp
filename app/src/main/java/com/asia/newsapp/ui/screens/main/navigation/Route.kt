package com.asia.newsapp.ui.screens.main.navigation

import android.os.Bundle
import com.asia.newsapp.R


const val navigationRouteMain = "main"
const val navigationRouteHome = "home"
const val navigationRouteBookmarked = "bookmarked"


sealed class Screen(
    val route: String,
    var routePath: String? = null,
    var clearBackStack: Boolean = false,
    val restoreState: Boolean = true,
    val selectedIcon: Int? = null,
    val unselectedIcon: Int? = null,
    var args: Bundle? = null
) {
    fun withClearBackStack() = apply { clearBackStack = true }

    fun routeWith(path: String) = apply {
        routePath = path
    }

    data object Main : Screen(navigationRouteMain)
    data object Home : Screen(
        route = navigationRouteHome,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected
    )

    data object Bookmarked : Screen(
        route = navigationRouteBookmarked,
        selectedIcon = R.drawable.ic_bookmarked_selected,
        unselectedIcon = R.drawable.ic_bookmarked_unselected

    )

}
