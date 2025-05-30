package com.example.myapp.data.api

import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// NewsAPI
interface NewsApi {
    @GET("v2/top-headlines")
    fun getTopHeadLinesResponse(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlinesResponse>
}