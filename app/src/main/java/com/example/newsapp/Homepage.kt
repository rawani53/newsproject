package com.example.newsapp

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun Homepage(viewModel: NewsviewModel) {

    val articles by viewModel.articles.observeAsState(emptyList()) // Different from what is taught
    // if : not work the uses : val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CategoriesBar(viewModel)

        LazyColumn(

            modifier = Modifier.fillMaxSize()

        ) {

            items(articles) { article ->
                ArticleItem(article)
            }


        }
    }


}

@Composable
fun CategoriesBar(viewModel: NewsviewModel) {

    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment  = Alignment.CenterVertically
    ){

        categoriesList.forEach { category ->
            Button(
                onClick = { },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = category)
            }
        }

    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.urlToImage?:"https://thumbs.dreamstime.com/b/image-not-available-icon-image-not-available-icon-set-default-missing-photo-stock-vector-symbol-black-filled-330178688.jpg",
                contentDescription = "article image",
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(8.dp)
            ) {
                Text(article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                )

                Text(
                    text = article.source.name,
                    maxLines = 1,
                    fontSize = 14.sp
                )
            }
        }
    }
}









































