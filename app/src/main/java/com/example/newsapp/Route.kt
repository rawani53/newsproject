package com.example.newsapp

import kotlinx.serialization.Serializable

@Serializable
object Loginscreen

@Serializable
object HomePageScreen

@Serializable
data class NewsArticleScreen(
    val url : String
)