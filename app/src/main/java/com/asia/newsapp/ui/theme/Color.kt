package com.asia.newsapp.ui.theme

import androidx.compose.ui.graphics.Color

val TernaryShadesDark = Color(0x6104030A)
val QuaternaryShadesDark = Color(0x2904030A)
val BackgroundLight = Color(0xFFF6F7F7)
val SecondaryLight = Color(0xFFF8CF54)


data class Colors(
    val primary: Color,
    val secondary : Color,
    val contentPrimary: Color,
    val onSecondary: Color,
    val contentTertiary: Color,
    val surface: Color,
    val onPrimary: Color,
    val background: Color,
    val border: Color,
    val onSurface: Color,
)

val LightColors = Colors(
        primary = Color(0xFFE5AD06),
        secondary = Color(0xFFF8CF54),
        onSecondary = Color(0xDEFFFFFF),
        contentPrimary = Color(0xFF222222),
        contentTertiary = Color(0xFF6D6D6D),
        surface = QuaternaryShadesDark,
        onSurface = TernaryShadesDark,
        background = Color(0xFFFAFAFA),
        border = Color(0xFFE6E6E6),
        onPrimary = Color(0xFFFFFFFF),
)

val DarkColors = Colors(
        primary = Color(0xFFE5AD06),
        secondary =  Color(0xFFF8CF54),
        onSecondary = Color(0xDEFFFFFF),
        contentPrimary = Color(0xFF222222),
        contentTertiary = Color(0xFF6D6D6D),
        surface = QuaternaryShadesDark,
        onSurface = TernaryShadesDark,
        background = Color(0xFF1E1F24),
        border = Color(0xFFE6E6E6),
        onPrimary = Color(0xFFFFFFFF),
)