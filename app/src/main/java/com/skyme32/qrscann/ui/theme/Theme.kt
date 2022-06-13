package com.skyme32.qrscann.ui.theme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/*
<color name="primary"></color>
<color name="primary_dark">#C</color>
<color name="primary_light">#</color>
<color name="accent">#CDDC39</color>
<color name="primary_text">#212121</color>
<color name="secondary_text">#757575</color>
<color name="icons">#FFFFFF</color>
<color name="divider">#BDBDBD</color>
 */
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

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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