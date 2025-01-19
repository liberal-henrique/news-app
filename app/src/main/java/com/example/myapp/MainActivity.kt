package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapp.presentation.ui.screens.NewsListScreen
import com.example.myapp.presentation.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {

    val news = NewsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsListScreen(news)
        }
    }
}
