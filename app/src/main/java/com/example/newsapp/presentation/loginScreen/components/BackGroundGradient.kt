package com.example.newsapp.presentation.loginScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BackGroundGradient(modifier: Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color.Magenta.copy(.3f),
                        MaterialTheme.colorScheme.primary.copy(.3f),
                        Color.Blue.copy(.6f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1200f, 1200f)
                )
            )
    )
}