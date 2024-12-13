package com.example.newsapp.Presentation.Homepage.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxGradient(){
    Box(
        modifier = Modifier.size(395.dp, 60.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent ),
                    startY = 4f
                )
            )
    )
}