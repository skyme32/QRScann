package com.skyme32.qrscann.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = PrimaryGreen,
    primaryVariant = DarkGrenn,
    secondary = OilGreen,
)

private val LightColorPalette = lightColors(
    primary = PrimaryGreen,
    primaryVariant = DarkGrenn,
    secondary = OilGreen,
    onSurface = DarkGrenn
)

@Composable
fun QRScannTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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