package com.example.newsapp.Presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
object Loginscreen

@Serializable
object HomePageScreen

@Serializable
data class NewsArticleScreen(
    val url: String
)