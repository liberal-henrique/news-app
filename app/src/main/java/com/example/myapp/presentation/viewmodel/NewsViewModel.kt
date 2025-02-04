package com.example.myapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.Article
import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        newsRepository.getTopHeadLines(
            country = "us",
            apiKey = "6444c6d8fdda4861aa9609eb13fe74b8",
            callback = object : Callback<TopHeadlinesResponse> {
                override fun onResponse(
                    call: Call<TopHeadlinesResponse>,
                    response: Response<TopHeadlinesResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        response.body()?.let { responseBody ->
                            Log.i(TAG, "onResponse: ${responseBody.articles}")
                            _articles.value = responseBody.articles
                        }
                    }
                    else {
                        _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                    _isLoading.value = false
                    _errorMessage.value = "Failed to fetch news: ${t.message}"
                }
            }
        )
    }
}