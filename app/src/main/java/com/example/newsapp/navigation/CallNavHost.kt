package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.presentation.homePage.viewModel.NewsViewModel
import com.example.newsapp.presentation.homePage.Homepage
import com.example.newsapp.presentation.loginScreen.LoginScreen
import com.example.newsapp.presentation.newsArticlePage.NewsArticlepage

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