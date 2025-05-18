package com.example.myapp.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.myapp.presentation.navigation.Screen
import com.example.myapp.presentation.ui.component.NewsItem
import com.example.myapp.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun NewsListScreen(
    navController: NavHostController,
    viewModel: NewsViewModel
) {
    val context = LocalContext.current
    val prefs = remember(context) {
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    val scrollPosition = prefs.getInt("scroll_position", 0)

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
            val lazyListState = rememberLazyListState(
                initialFirstVisibleItemIndex = scrollPosition
            )

            LaunchedEffect(lazyListState) {
                snapshotFlow {
                    lazyListState.firstVisibleItemIndex
                }
                    .debounce(500L)
                    .collectLatest { index ->
                        prefs.edit()
                            .putInt("scroll_position", index)
                            .apply()
                }
            }

            LazyColumn(
                state = lazyListState
            ) {
                items(articles) { article ->
                    NewsItem(
                        article,
                        onClick = { sourceId ->
                            val route = Screen.NewsDetail.createRoute(sourceId)
                            navController.navigate(route)
                        }
                    )
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