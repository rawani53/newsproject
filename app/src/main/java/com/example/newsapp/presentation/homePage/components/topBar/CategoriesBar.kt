package com.example.newsapp.presentation.homePage.components.topBar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.newsapp.presentation.homePage.viewModel.NewsViewModel

@Composable
fun CategoriesBar(viewModel: NewsViewModel) {
    Column() {
        GetSearchBar(viewModel)
        GetScrollableTabRow(viewModel)
    }
}

