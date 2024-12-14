package com.example.newsapp.Presentation.Homepage.Components.topBar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.newsapp.data.api.NewsViewModel

@Composable
fun CategoriesBar(viewModel: NewsViewModel) {
    Column() {
        GetSearchBar(viewModel)
        GetScrollableTabRow(viewModel)
    }
}

