package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.presentation.navigation.CustomNavHost
import com.example.myapp.presentation.viewmodel.NewsViewModel
import com.example.myapp.presentation.viewmodel.NewsViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository: NewsRepository = NewsRepository()
    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CustomNavHost(
                navController = navController,
                newsViewModel = newsViewModel
            )
        }
    }
}