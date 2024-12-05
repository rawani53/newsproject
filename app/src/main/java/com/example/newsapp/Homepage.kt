package com.example.newsapp

import android.graphics.Typeface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import java.security.KeyStore.TrustedCertificateEntry


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(viewModel: NewsviewModel, navController: NavHostController) {

    val articles by viewModel.articles.observeAsState(emptyList()) // Different from what is taught
    // if : not work the uses : val articles by newsViewModel.articles.observeAsState(emptyList())

    // var loading = viewModel.loading.value

    val navTabItems = listOf(

        bottomnavdataitem(
            title = "Home",
            SelectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        bottomnavdataitem(
            title = "Saved",
            SelectedIcon = Icons.Filled.Star,
            unSelectedIcon = Icons.Outlined.Star,
            hasNews = false,
            badgeCount = 7
        ),
        bottomnavdataitem(
            title = "Settings",
            SelectedIcon = Icons.Filled.Settings,
            unSelectedIcon = Icons.Outlined.Settings,
            hasNews = true
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(

        bottomBar = {

            NavigationBar {

                navTabItems.forEachIndexed { index, item ->

                    NavigationBarItem(

                        selected = selectedItemIndex == index,

                        onClick = { selectedItemIndex = index },

                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null
                                    ) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (
                                        selectedItemIndex == index) {
                                        item.SelectedIcon
                                    } else {
                                        item.unSelectedIcon
                                    },
                                    contentDescription = item.title
                                )
                            }
                        },

                        label = { Text(item.title) }

                    )
                }

            }

        }

    ) { innerPadding ->


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box {


//            Image(
//                painter = painterResource(R.drawable.bg),
//                contentDescription = "bg",
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier.fillMaxSize()
//            )

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {


                    CategoriesBar(viewModel)


                    // CircularIndeterminateProgressBar(isDisplayed = loading)


                    LazyColumn(

                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        items(articles) { article ->
                            ArticleItem(article, navController)
                            //HorizontalDivider(modifier = Modifier.width(350.dp), thickness = 1.dp)
                        }


                    }
                }
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

    var selectedTabIndex by remember {
        mutableStateOf(0)
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
    Column() {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .height(25.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (isSearchExpanded) {

                OutlinedTextField(

                    modifier = Modifier
                        .padding(8.dp)
                        .height(48.dp)
                        .width(394.dp)
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

                Text(
                    "NewsNation",
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ""
                    )
                }
            }
        }

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
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


@Composable
fun ArticleItem(article: Article, navController: NavHostController) {

    Card(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),

        elevation = CardDefaults.cardElevation(5.dp),

        onClick = {
            navController.navigate(NewsArticleScreen(article.url))
        },

        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),

        shape = RoundedCornerShape(30.dp)
    ) {


        Column(verticalArrangement = Arrangement.Top) {


            AsyncImage(
                model = article.urlToImage
                    ?: "https://thumbs.dreamstime.com/b/image-not-available-icon-image-not-available-icon-set-default-missing-photo-stock-vector-symbol-black-filled-330178688.jpg",
                contentDescription = "article image",
                modifier = Modifier.size(395.dp, 175.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                article.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                fontSize = 18.sp,
                color = Color.Unspecified,
                //fontFamily = FontFamily(Typeface.SANS_SERIF),
                modifier = Modifier.padding(
                    top = 6.dp,
                    start = 10.dp,
                    end = 6.dp,
                    bottom = 6.dp
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(40.dp, 40.dp)
                        .padding(
                            bottom = 4.dp,
                            start = 8.dp
                        )
                ) {

                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = "",
                        modifier = Modifier.size(40.dp,40.dp)
                    )

                }


                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(40.dp, 40.dp)
                        .padding(
                            bottom = 4.dp,
                            end = 4.dp
                        )
                ) {

                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp,30.dp)
                    )

                }

            }


            //sube2
            //  Text(
            //         text = article.source.name,
            //         maxLines = 1,
            //         fontSize = 14.sp
            //    )


        }
    }
}





































