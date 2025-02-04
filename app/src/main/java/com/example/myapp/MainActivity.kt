package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapp.data.api.MyApi
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.presentation.ui.screens.NewsListScreen
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
            NewsListScreen(newsViewModel)
        }
    }
}