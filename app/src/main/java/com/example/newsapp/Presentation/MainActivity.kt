package com.example.newsapp.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.Navigation.HomePageScreen
import com.example.newsapp.Logic.NewsviewModel
import com.example.newsapp.Navigation.Loginscreen
import com.example.newsapp.Navigation.NewsArticleScreen
import com.example.newsapp.Presentation.Homepage.Homepage
import com.example.newsapp.Presentation.Loginscreen.LoginScreen
import com.example.newsapp.Presentation.newsArticlePage.NewsArticlepage
import com.example.newsapp.ui.theme.NewsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val viewModel: NewsviewModel = viewModel()
            NewsappTheme {


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
        }
    }
}






