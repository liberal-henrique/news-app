package com.example.myapp.data.repository

import com.example.myapp.BuildConfig
import com.example.myapp.data.api.NewsApi
import com.example.myapp.data.api.NewsDataSource
import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Callback

class NewsApiDataSource(
    private val service: NewsApi
): NewsDataSource {
    override fun getTopHeadlines(callback: Callback<TopHeadlinesResponse>) {
        service.getTopHeadLinesResponse(
            country = BuildConfig.COUNTRY,
            apiKey = BuildConfig.NEWS_API_KEY
        ).enqueue(callback)
    }
}