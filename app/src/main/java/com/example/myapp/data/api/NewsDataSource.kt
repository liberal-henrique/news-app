package com.example.myapp.data.api

import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Callback

interface NewsDataSource {
    fun getTopHeadlines(callback: Callback<TopHeadlinesResponse>)
}