package com.example.newsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsviewModel : ViewModel() {

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines() {

        val newsApiClient = NewsApiClient(constant.apikey)

        val request = TopHeadlinesRequest.Builder().language("en").build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.forEach {
                    Log.i("NEWSAPI Response", it.title)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NEWSAPI Response failed", throwable.localizedMessage)
                }

            }

        })
    }

}