package com.example.myapp.data.api

import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

// New York Times
interface NytApiService {
    @GET("v1/{year}/{month}.json?api-key=")
    fun getTopStories(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("api-key") apiKey: String
    ): Call<TopHeadlinesResponse>
}
