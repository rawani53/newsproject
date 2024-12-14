package com.example.newsapp.Presentation.Homepage.Components.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.newsapp.Presentation.Navigation.NewsArticleScreen
import com.example.newsapp.R
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun CardComponent( article: Article, navController: NavHostController, removednewsimage: String,
                   boxGradient : @Composable () -> Unit = {} ){
    Card(
        modifier = Modifier.padding(14.dp).size(396.dp, 398.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = { navController.navigate(NewsArticleScreen(article.url)) },
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            Box( contentAlignment = Alignment.TopEnd){
                AsyncImage(
                    model = article.urlToImage ?: removednewsimage,
                    contentDescription = "article image",
                    modifier = Modifier.size(395.dp, 285.dp),
                    contentScale = ContentScale.Crop )
                boxGradient()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp, 40.dp)
                            .padding(top = 8.dp, bottom = 4.dp, start = 8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp, 40.dp)
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp, 40.dp)
                            .padding(top = 8.dp, bottom = 4.dp, end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp, 30.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text( article.title, fontWeight = FontWeight.SemiBold, maxLines = 2, fontSize = 18.sp,
                color = Color.Unspecified, modifier = Modifier.padding( horizontal = 15.dp ))
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier.padding( horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painterResource(R.drawable.author),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                    Text(
                        text = article.author ?: "no article found",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        fontSize = 15.sp,
                        color = Color.Unspecified,
                        modifier = Modifier.padding(start = 6.dp).width(160.dp),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier.padding( horizontal = 15.dp ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                    Text(
                        text = article.publishedAt.formatToReadableDate()
                            ?: "no article found",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        fontSize = 15.sp,
                        color = Color.Unspecified,
                        modifier = Modifier.padding( start = 6.dp )
                    )
                }
            }
        }
    }
}




