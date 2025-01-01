package com.example.newsapp.presentation.homePage.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.RetrofitInstance
import com.example.newsapp.model.homePage.Article
import com.example.newsapp.model.homePage.NewsResponse
import com.example.newsapp.utils.constant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class NewsViewModel : ViewModel() {


    val _articles = MutableLiveData<List<Article>>()
    val articles: MutableLiveData<List<Article>> = _articles
    val loading = mutableStateOf(false)

    fun fetchNewsTopHeadlines(category: String = "general") {   // CATEGORIES AND GENERAL

        viewModelScope.launch {
            loading.value = true

            val response = RetrofitInstance.api.fetchNewsTopHeadlines(category = category)
            response.body()?.articles?.let {
                it.forEach { art -> Log.d("API_OUTPUT", art.publishedAt) }
                _articles.postValue(it)
                Log.d("APi", "Search results fetched")
                loading.value = false

            }

        }

    }


    fun fetchEverythingWithQuery(query: String) {  //SEARCH
        viewModelScope.launch {
            loading.value = true

            val response: Response<NewsResponse> =
                RetrofitInstance.api.fetchEverythingWithQuery(searchQuery = query)

            if (response.isSuccessful) {

                response.body()?.articles?.let {
                    _articles.postValue(it)

                    Log.d("APi", "Search results fetched")
                    loading.value = false
                }
            } else {
                Log.d("api", "api failed")
            }
        }
    }
}



