package com.example.myapp.presentation.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapp.presentation.ui.component.NewsContentScreen
import com.example.myapp.presentation.ui.screens.NewsListScreen
import com.example.myapp.presentation.viewmodel.NewsViewModel

@Composable
fun CustomNavHost(
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsList.route
    ) {
        composable(route = Screen.NewsList.route) {
            NewsListScreen(
                navController,
                newsViewModel
            )
        }
        composable(route = "news_detail/{articleId}") { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")
            val article = newsViewModel.articles.value.find { it.id == articleId }
            Log.d("NAVIGATION", "Article ID: $articleId - Found: ${article != null}")
            article?.let {
                NewsContentScreen(
                    article = it,
                    navController = navController
                )
            } ?: Text("Article not found.")
        }
    }
}