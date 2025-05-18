package com.example.myapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.data.repository.NewsRepositoryFactory
import com.example.myapp.presentation.common.BiometricPromptManager
import com.example.myapp.presentation.ui.component.BiometricScreen
import com.example.myapp.presentation.viewmodel.NewsViewModel
import com.example.myapp.presentation.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    private val repository: NewsRepository = NewsRepositoryFactory.create()
    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiometricScreen(
                promptManager = promptManager,
                newsViewModel = newsViewModel
            )
        }
    }
}