package com.example.myapp.data.repository

import com.example.myapp.data.api.MyApi
import com.example.myapp.domain.model.TopHeadlinesResponse
import com.example.myapp.data.api.RetrofitProvider
import retrofit2.Callback

class NewsRepository(
    private val apiService: MyApi = RetrofitProvider
        .provideRetrofit("https://newsapi.org/")
        .create(MyApi::class.java)
) {

    fun getTopHeadLines(
        country: String,
        apiKey: String,
        callback: Callback<TopHeadlinesResponse>
    ) {
        apiService.getTopHeadLinesResponse(country, apiKey).enqueue(callback)
    }
}