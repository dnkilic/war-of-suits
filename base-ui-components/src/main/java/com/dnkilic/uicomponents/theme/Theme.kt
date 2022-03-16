package com.dnkilic.uicomponents.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkerGray,
    secondary = PinkishWhite,
    secondaryVariant = PinkerWhite,
    background = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = DarkGray,
    secondary = PinkishWhite,
    secondaryVariant = PinkerWhite,
    background = Color.White,
)

@Composable
fun WarOfSuitsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object AppTheme {
    val spaces: Spaces
        @Composable
        @ReadOnlyComposable
        get() = LocalDimension.current
}

private val LocalDimension = staticCompositionLocalOf {
    Spaces()
}