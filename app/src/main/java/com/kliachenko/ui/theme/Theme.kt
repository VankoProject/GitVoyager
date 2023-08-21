package com.kliachenko.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = Color.LightGray,
    onBackground = Color.White,
    surface = Color.Blue,
    onSurface = Color.DarkGray,
)

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    */


@Composable
fun GitVoyagerTheme(darkTheme: Boolean = true, content: @Composable () -> Unit) {

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}