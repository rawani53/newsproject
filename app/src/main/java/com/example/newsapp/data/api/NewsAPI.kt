package com.example.newsapp.data.api

import com.example.newsapp.model.homePage.NewsResponse
import com.example.newsapp.utils.constant.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/top-headlines")
    suspend fun fetchNewsTopHeadlines(
        @Query("apiKey")
        apiKey: String = BASE_URL,
        @Query("category")
        category: String,
        @Query("country")
        country: String = "us"
    ): Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun fetchEverythingWithQuery(
        @Query("apiKey")
        apiKey: String = BASE_URL,
        @Query("q")
        searchQuery : String
    ): Response<NewsResponse>


}