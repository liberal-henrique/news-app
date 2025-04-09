package com.example.myapp.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.Article
import com.example.myapp.domain.model.TopHeadlinesResponse
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
        Log.d(TAG, "How many times I am calling fetchNews()?")
        if (isFetched) return
        isFetched = true

        _isLoading.value = true
        _errorMessage.value = null

        newsRepository.getTopHeadLines(
            callback = object : Callback<TopHeadlinesResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<TopHeadlinesResponse>,
                    response: Response<TopHeadlinesResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        response.body()?.let { responseBody ->

                            val updatedArticles = responseBody.articles.mapIndexed { index, article ->
                                val newId = UUID.randomUUID().toString()
                                val newSource = article.source.copy(id = newId)
                                article.copy(source = newSource)
                            }
                            _articles.value = updatedArticles.sortedByDescending {
                                OffsetDateTime.parse(it.publishedAt)
                            }
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