package com.asia.newsapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val newsTypography = NewsTypography(
        headlineLarge = TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 24.sp,
                lineHeight = 32.4.sp,
                fontWeight = FontWeight.W600,
        ),
        headline = TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
        ),
        titleLarge = TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W600,
        ),
        title =TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 22.sp
        ),
        titleMedium = TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
        ),
        body = TextStyle(
                fontFamily = PlusJakartaSans,
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight.W400,
        ),
        caption =TextStyle(
                fontFamily = PlusJakartaSans,
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
    val body: TextStyle = TextStyle(),
    val caption: TextStyle = TextStyle(),
)
