package com.example.myapp.data.api


import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("v2/top-headlines")
    fun getTopHeadLinesResponse(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlinesResponse>
}