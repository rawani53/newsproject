package com.example.newsapp.model.homePage

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)