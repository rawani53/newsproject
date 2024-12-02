package com.example.newsapp

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable

fun NewsArticlepage (url : String){
    AndroidView(factory = {context ->
        WebView(context).apply{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}