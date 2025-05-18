package com.example.myapp.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.Article
import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.OffsetDateTime
import java.util.UUID

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val TAG: String = "CHECK_RESPONSE"

    private var isFetched = false

    fun fetchNews() {
        if (isFetched) return
        isFetched = true

        _isLoading.value = true
        _errorMessage.value = null

        newsRepository.getTopHeadLines{ result ->
            _isLoading.value = false
            result
                .onSuccess { articles ->
                    _articles.value = articles.sortedByDescending { it.publishedAt }
                }
                .onFailure { throwable ->
                    _errorMessage.value = throwable.message
                }
        }
    }
}