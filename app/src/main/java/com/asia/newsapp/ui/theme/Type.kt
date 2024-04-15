package com.asia.newsapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val newsTypography = NewsTypography(
        headlineLarge = TextStyle(
                fontFamily = archivo,
                fontSize = 24.sp,
                lineHeight = 32.4.sp,
                fontWeight = FontWeight.W600,
        ),
        headline = TextStyle(
                fontFamily = archivo,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
        ),
        titleLarge = TextStyle(
                fontFamily = archivo,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W600,
        ),
        titleMedium = TextStyle(
                fontFamily = archivo,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
        ),
        title =TextStyle(
                fontFamily = archivo,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 22.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = archivo,
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight.W400,
        ),
        body = TextStyle(
                fontFamily = archivo,
                fontSize = 12.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight.W600,
        ),
        caption =TextStyle(
                fontFamily = archivo,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 15.sp
        ),
)
data class NewsTypography(
    val headlineLarge: TextStyle = TextStyle(),
    val headline: TextStyle = TextStyle(),
    val titleLarge: TextStyle = TextStyle(),
    val title: TextStyle = TextStyle(),
    val titleMedium: TextStyle = TextStyle(),
    val bodyMedium: TextStyle = TextStyle(),
    val body: TextStyle = TextStyle(),
    val caption: TextStyle = TextStyle(),
)
