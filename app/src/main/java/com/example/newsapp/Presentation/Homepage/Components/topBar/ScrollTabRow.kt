package com.example.newsapp.Presentation.Homepage.Components.topBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.api.NewsViewModel

@Composable
fun GetScrollableTabRow(viewModel: NewsViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        edgePadding = 17.dp
    ) {
        categoriesList.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    viewModel.fetchNewsTopHeadlines(item)
                    selectedTabIndex = index
                },
                text = { Text(text = item) },
                unselectedContentColor = MaterialTheme.colorScheme.outline,
                selectedContentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}