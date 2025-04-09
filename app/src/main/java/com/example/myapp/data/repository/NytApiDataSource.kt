package com.example.myapp.data.repository

import com.example.myapp.BuildConfig
import com.example.myapp.data.api.NewsDataSource
import com.example.myapp.data.api.NytApiService
import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Callback

class NytApiDataSource(
    private val service: NytApiService
): NewsDataSource {
    override fun getTopHeadlines(callback: Callback<TopHeadlinesResponse>) {
        service.getTopStories(
            year = 2024,
            month = 1,
            apiKey = BuildConfig.NEWS_API_KEY
        ).enqueue(callback)
    }
}