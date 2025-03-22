package com.example.myapp.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapp.presentation.ui.component.NewsItem
import com.example.myapp.presentation.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(viewModel: NewsViewModel) {
    val articles by viewModel.articles
    val errorMessage by viewModel.errorMessage
    val isLoading by viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage ?: "Unknown error")
            }
        }
        articles.isNotEmpty() -> {
            LazyColumn {
                items(articles) { article ->
                    NewsItem(article)
                }
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No articles found.")
            }
        }
    }
}