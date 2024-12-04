package com.example.newsapp

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.newsapp.ui.theme.NewsappTheme
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun Homepage(viewModel: NewsviewModel, navController: NavHostController) {

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
                ArticleItem(article, navController)
            }


        }
    }


}

@Composable
fun CategoriesBar(viewModel: NewsviewModel) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    var isSearchExpanded by remember {
        mutableStateOf(false)
    }

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
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSearchExpanded) {

            OutlinedTextField(

                modifier = Modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape),

                value = searchQuery,
                onValueChange = { searchQuery = it },

                trailingIcon = {

                    IconButton(onClick = {
                        isSearchExpanded = false

                        if (searchQuery.isNotEmpty()) {
                            viewModel.fetchEverythingWithQuery(searchQuery)
                        }
                    }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "collapse search bar"
                        )
                    }

                })

        } else {

            IconButton(onClick = {
                isSearchExpanded = true

            }) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            }
        }

        categoriesList.forEach { category ->
            Button(
                onClick = { viewModel.fetchNewsTopHeadlines(category) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = category)
            }
        }

    }
}

@Composable
fun ArticleItem(article: Article, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { navController.navigate(NewsArticleScreen(article.url)) },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {

        //  Column (modifier = Modifier
        //      .fillMaxSize(),
        //      verticalArrangement = Arrangement.Top,
        //      horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            //element1
            AsyncImage(
                model = article.urlToImage
                    ?: "https://thumbs.dreamstime.com/b/image-not-available-icon-image-not-available-icon-set-default-missing-photo-stock-vector-symbol-black-filled-330178688.jpg",
                contentDescription = "article image",
                modifier = Modifier.size(400.dp, 250.dp),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
            ) { }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(8.dp), contentAlignment = Alignment.BottomStart
            ) {

                Text(
                    article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.SERIF)
                )


                //sube2
                //  Text(
                //         text = article.source.name,
                //         maxLines = 1,
                //         fontSize = 14.sp
                //    )
            }

        }
    }
}
//}






































