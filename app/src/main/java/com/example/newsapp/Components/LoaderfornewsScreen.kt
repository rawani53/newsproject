package com.example.newsapp.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay


@Composable
fun DelayedProgressBar(delayMillis :  Long) {

    var showProgress by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        showProgress = true

        delay(delayMillis)

        showProgress = false
    }

    // Display the progress bar conditionally
    if (showProgress) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}