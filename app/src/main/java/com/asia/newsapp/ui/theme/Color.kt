package com.asia.newsapp.ui.theme

import androidx.compose.ui.graphics.Color

val TernaryShadesDark = Color(0x6104030A)
val QuaternaryShadesDark = Color(0x2904030A)
val BackgroundLight = Color(0xFFF6F7F7)
val SecondaryLight = Color(0xFFDDEDFF)


data class Colors(
    val primary: Color,
    val contentPrimary: Color,
    val secondary : Color,
    val onSecondary: Color,
    val contentTertiary: Color,
    val surface: Color,
    val onPrimary: Color,
    val background: Color,
    val green: Color,
    val onSurface: Color,
)

val LightColors = Colors(
        primary = Color(0xFF585896),
        secondary = SecondaryLight,
        onSecondary = Color(0xDEFFFFFF),
        contentPrimary = Color(0xDE000A1F),
        contentTertiary = Color(0x61000B1F),
        surface = QuaternaryShadesDark,
        background = Color(0xFFFAFAFA),
        green = Color(0xFF31B43B),
        onPrimary = Color(0xFFECF2FF),
        onSurface = TernaryShadesDark,
)

val DarkColors = Colors(
        primary = Color(0xFF42426F),
        secondary = SecondaryLight,
        onSecondary = Color(0xDEFFFFFF),
        contentPrimary = Color(0xDEFFFFFF),
        contentTertiary = Color(0x61FFFFFF),
        surface = QuaternaryShadesDark,
        background = Color(0xFF1E1F24),
        green = Color(0xFF31B43B),
        onPrimary = Color(0xFFECF2FF),
        onSurface = TernaryShadesDark,
)