package com.example.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsviewModel : ViewModel() {

    init {
        fetchNewsTopHeadlines()
    }

    val _articles = MutableLiveData<List<Article>>()
    val articles : LiveData<List<Article>> = _articles

    fun fetchNewsTopHeadlines() {

        val newsApiClient = NewsApiClient(constant.apikey)

        val request = TopHeadlinesRequest.Builder().language("en").build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
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