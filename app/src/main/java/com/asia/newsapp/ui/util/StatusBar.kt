package com.asia.newsapp.ui.util

import androidx.compose.ui.graphics.Color
import com.asia.newsapp.ui.theme.BackgroundLight
import com.google.accompanist.systemuicontroller.SystemUiController

fun setStatusBarColor(
    statusBarColor: Color = BackgroundLight,
    navigationBarColor: Color = BackgroundLight,
    systemUIController: SystemUiController,
    isDarkIcon: Boolean = true
) {
    systemUIController.setStatusBarColor(statusBarColor, darkIcons = isDarkIcon)
    systemUIController.setNavigationBarColor(navigationBarColor, darkIcons = isDarkIcon)
}