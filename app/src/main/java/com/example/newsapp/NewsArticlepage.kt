package com.example.newsapp

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.coroutineScope


@Composable
fun NewsArticlepage(url: String) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })

        DelayedProgressBar(5000)

    }

}

