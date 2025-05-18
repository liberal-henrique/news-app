package com.example.myapp.data.api

import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import com.example.myapp.data.api.dto.nyt.NytArchiveResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

// New York Times
interface NytApiService {
    @GET("v1/{year}/{month}.json?")
    fun getTopStories(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("api-key") apiKey: String
    ): Call<NytArchiveResponse>
}