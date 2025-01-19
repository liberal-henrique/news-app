package com.example.myapp.presentation.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.example.myapp.presentation.ui.component.NewsItem
import com.example.myapp.presentation.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(viewModel: NewsViewModel) {
    val articles by viewModel.articles

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    LazyColumn {
        items(articles) { article ->
            NewsItem(article)
        }
    }
}
