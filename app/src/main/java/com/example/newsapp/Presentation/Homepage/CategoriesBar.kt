package com.example.newsapp.Presentation.Homepage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.Logic.NewsviewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesBar(viewModel: NewsviewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false)}
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

    Column() {

        if (isSearchExpanded) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("know whats going on ....") },
                    modifier = Modifier.padding(7.dp).height(53.dp).fillMaxHeight().width(394.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp)),
                    trailingIcon = {
                        IconButton(onClick = {
                            isSearchExpanded = false
                            if (searchQuery.isNotEmpty()) { viewModel.fetchEverythingWithQuery(searchQuery) }
                        }){
                            Icon(imageVector = Icons.Default.Search,
                                contentDescription = "collapse search bar"
                            )
                        }
                    }
                )
            }

        }else{
            Row(
                modifier = Modifier.fillMaxWidth().padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { isSearchExpanded = true }){
                    Icon( imageVector = Icons.Default.Search, contentDescription = "Search icon")
                }
                Text( "NewsNation", fontWeight = FontWeight.Bold)
                IconButton(onClick = {}) {
                    Icon( imageVector = Icons.Default.MoreVert, contentDescription = "" )
                }
            }
        }

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
}

