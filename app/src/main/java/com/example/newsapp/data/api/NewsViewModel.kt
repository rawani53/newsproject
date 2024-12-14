package com.example.newsapp.data.api

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {

    val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    val loading = mutableStateOf(false)

    fun fetchEverythingWithQuery(query: String) {
        viewModelScope.launch {
            loading.value = true

            val newsApiClient = NewsApiClient(constant.apikey)
            val request = EverythingRequest.Builder().language("en").q(query).build()

            newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse?) {
                    response?.articles?.let {
                        _articles.postValue(it)
                        Log.d("APi", "Search results fetched")
                        loading.value = false
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

    fun fetchNewsTopHeadlines(category: String = "GENERAL") {
        viewModelScope.launch {
            loading.value = true
            delay(2000)

            val newsApiClient = NewsApiClient(constant.apikey)
            val request = TopHeadlinesRequest.Builder().language("en").category(category).build()

            newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse?) {
                    response?.articles?.let { it ->
                        it.forEach { art -> Log.d("API_OUTPUT", art.publishedAt) }
                        _articles.postValue(it)
                        loading.value = false
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
}