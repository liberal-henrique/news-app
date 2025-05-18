package com.example.myapp.data.repository

import com.example.myapp.data.source.NewsDataSource
import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import com.example.myapp.domain.model.Article
import retrofit2.Callback

class NewsRepository(
    private val dataSource: NewsDataSource
) {
    fun getTopHeadLines(callback: (Result<List<Article>>) -> Unit) {
        dataSource.getTopHeadlines(callback)
    }
}