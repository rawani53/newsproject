package com.example.newsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CircularIndeterminateProgressBar (
    isDisplayed: Boolean
){

    if(isDisplayed){

        Row (

            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black
                )
                .padding(bottom = 125.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ){

            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )

        }

    }
}