package com.example.myapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.BuildConfig
import com.example.myapp.data.api.NewsApi
import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import com.example.myapp.data.mapper.toArticles
import com.example.myapp.data.source.NewsDataSource
import com.example.myapp.domain.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsApiDataSource(
    private val service: NewsApi
): NewsDataSource {
    override fun getTopHeadlines(callback: (Result<List<Article>>) -> Unit) {
        service.getTopHeadLinesResponse(
            country = BuildConfig.COUNTRY,
            apiKey = BuildConfig.NEWS_API_KEY
        ).enqueue(object : Callback<TopHeadlinesResponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<TopHeadlinesResponse>,
                response: Response<TopHeadlinesResponse>
            ) {
                if (response.isSuccessful) {
                    val articles = response.body()?.toArticles() ?: emptyList()
                    callback(Result.success(articles))
                } else {
                    callback(Result.failure(Throwable("Error: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }
}