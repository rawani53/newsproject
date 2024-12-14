package com.example.newsapp.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.data.api.NewsViewModel
import com.example.newsapp.Presentation.Homepage.Homepage
import com.example.newsapp.Presentation.Loginscreen.LoginScreen
import com.example.newsapp.Presentation.newsArticlePage.NewsArticlepage

@Composable
fun CallNavHost(){
    val navController = rememberNavController()
    val viewModel: NewsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Loginscreen
    ) {
        composable<Loginscreen> {
            LoginScreen(viewModel, navController)
        }
        composable<HomePageScreen> {
            Homepage(viewModel, navController)
        }
        composable<NewsArticleScreen> {
            val args = it.toRoute<NewsArticleScreen>()
            NewsArticlepage(args.url)
        }
    }
}