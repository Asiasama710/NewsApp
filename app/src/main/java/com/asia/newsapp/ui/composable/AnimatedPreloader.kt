package com.asia.newsapp.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.asia.newsapp.R
import com.asia.newsapp.ui.theme.Theme

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                    R.raw.loading_wifi
            )
    )

    val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
    )

    val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                    property = LottieProperty.COLOR_FILTER,
                    value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                          Theme.colors.primary.hashCode(),
                            BlendModeCompat.SRC_ATOP
                    ),
                    keyPath = arrayOf("**")
            )
    )
    LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = modifier,
            dynamicProperties =dynamicProperties
    )

}

