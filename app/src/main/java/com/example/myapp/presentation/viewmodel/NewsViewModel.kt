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

class NewsViewModel: ViewModel() {
    private val _articles = mutableStateOf<List<Article>>(emptyList())
    var articles: State<List<Article>> = _articles

    private val newsRepository = NewsRepository()
    private val TAG: String = "CHECK_RESPONSE"

    fun fetchNews() {
        newsRepository.getTopHeadLines(
            country = "us",
            apiKey = "6444c6d8fdda4861aa9609eb13fe74b8",
            callback = object : Callback<TopHeadlinesResponse> {
                override fun onResponse(
                    call: Call<TopHeadlinesResponse>,
                    response: Response<TopHeadlinesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { responseBody ->
                            Log.i(TAG, "onResponse: ${responseBody.articles}")
                            _articles.value = responseBody.articles
                        }
                    }
                }

                override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            }
        )
    }
}