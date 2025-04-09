package com.example.myapp.data.repository

import com.example.myapp.data.api.NewsDataSource
import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Callback

class NewsRepository(
    private val dataSource: NewsDataSource
) {
    fun getTopHeadLines(callback: Callback<TopHeadlinesResponse>) {
        dataSource.getTopHeadlines(callback)
    }
}