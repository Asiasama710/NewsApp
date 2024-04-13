package com.asia.newsapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LocalColorScheme = staticCompositionLocalOf { LightColors }
private val LocalTypography = staticCompositionLocalOf { NewsTypography() }


object Theme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: NewsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}


@Composable
fun NewsAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColors else LightColors
    val systemUiController = rememberSystemUiController()
    val darkIcons = !isDarkTheme

    DisposableEffect(systemUiController, darkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true,
            isNavigationBarContrastEnforced = false
        )
        onDispose {}
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides newsTypography,
    ) {
        content()
    }
}
