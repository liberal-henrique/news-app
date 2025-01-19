package com.example.myapp.data.repository

import com.example.myapp.data.api.MyApi
import com.example.myapp.domain.model.TopHeadlinesResponse
import com.example.myapp.data.api.RetrofitProvider
import retrofit2.Callback

class NewsRepository {

    private val BASE_URL = "https://newsapi.org/"

    private val apiService: MyApi = RetrofitProvider
        .provideRetrofit(BASE_URL)
        .create(MyApi::class.java)

    fun getTopHeadLines(
        country: String,
        apiKey: String,
        callback: Callback<TopHeadlinesResponse>
    ) {
        apiService.getTopHeadLinesResponse(country, apiKey).enqueue(callback)
    }
}