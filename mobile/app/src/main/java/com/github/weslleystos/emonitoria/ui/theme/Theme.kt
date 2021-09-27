package com.github.weslleystos.emonitoria.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val palette = lightColors(
        primary = Blue,
        primaryVariant = DarkBlue,
)

@Composable
fun AppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
            colors = palette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}