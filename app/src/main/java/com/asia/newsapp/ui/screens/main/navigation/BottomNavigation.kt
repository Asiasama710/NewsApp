package com.asia.newsapp.ui.screens.main.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.asia.newsapp.ui.theme.Theme

@Composable
fun BottomNavigation(
    screens: List<Screen>,
    onNavigateTo: (Screen) -> Unit,
    currentDestination: NavDestination?
) {

    AnimatedVisibility(visible = true) {
        AppBottomBar {
            screens.forEach { screen ->
                val selected: Boolean =
                    currentDestination?.hierarchy?.any { it.route == screen.route } ?: false
                AppBottomBarItem(
                        selected = selected,
                        onClick = { onNavigateTo(screen) },
                        title = screen.title?:"",
                        icon = {
                            Icon(
                                    painter = if (selected) {
                                        painterResource(id = screen.selectedIcon ?: 0)
                                    } else {
                                        painterResource(id = screen.unselectedIcon ?: 0)
                                    },
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                            )
                        },
                )
            }
        }
    }
}

@Composable
private fun AppBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {

    NavigationBar(
            modifier = modifier,
            containerColor = Theme.colors.background,
            contentColor = Theme.colors.surface,
            tonalElevation = 8.dp,
            content = content
    )
}

@Composable
private fun RowScope.AppBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    title:String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
            selected = selected,
            onClick = onClick,
            icon = if (selected) selectedIcon else icon,
            modifier = modifier,
            enabled = enabled,
            interactionSource = MutableInteractionSource(),
            label = {
                Text(
                    text = title,
                    color = if (selected) Theme.colors.primary else  Theme.colors.background,
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 20.dp)
                )
            },
            alwaysShowLabel = alwaysShowLabel,
            colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Theme.colors.primary,
                    unselectedIconColor = Theme.colors.surface,
                    selectedTextColor = Theme.colors.primary,
                    unselectedTextColor = Theme.colors.background,
                    indicatorColor = Theme.colors.background
            ),
    )
}
