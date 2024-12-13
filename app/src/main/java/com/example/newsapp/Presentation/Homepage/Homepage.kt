package com.example.newsapp.Presentation.Homepage

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.newsapp.Components.CircularIndeterminateProgressBar
import com.example.newsapp.Logic.NewsviewModel
import com.example.newsapp.Presentation.Homepage.Components.BottomNavBar
import com.example.newsapp.Presentation.Homepage.Components.BoxGradient
import com.example.newsapp.Presentation.Homepage.Components.CardComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(viewModel: NewsviewModel, navController: NavHostController) {
    val isLoading = viewModel.loading.value
    val articles by viewModel.articles.observeAsState(emptyList())// Different from what is taught
    // if : not work the uses : val articles by newsViewModel.articles.observeAsState(emptyList())
    val filteredArticles = articles.filterNotNull()
        .filter { !(it.urlToImage.isNullOrBlank() || it.author.isNullOrBlank() || it.publishedAt.isNullOrBlank() || it.url.isNullOrBlank()) }

    Scaffold(bottomBar = { BottomNavBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {
            CategoriesBar(viewModel)

            if (isLoading) {
                CircularIndeterminateProgressBar(isDisplayed = isLoading)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(filteredArticles) { article ->
                        CardComponent(
                            article = article,
                            navController = navController,
                            boxGradient = { BoxGradient() },
                            removednewsimage = "https://thumbs.dreamstime.com/b/image-not-available-icon-image-not-available-icon-set-default-missing-photo-stock-vector-symbol-black-filled-330178688.jpg",

                        )
                    }
                }
            }
        }
    }
}






