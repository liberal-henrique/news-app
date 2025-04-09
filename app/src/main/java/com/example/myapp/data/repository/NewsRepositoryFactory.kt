package com.example.myapp.data.repository

import com.example.myapp.BuildConfig
import com.example.myapp.data.api.NewsApi
import com.example.myapp.data.api.NewsDataSource
import com.example.myapp.data.api.NytApiService
import com.example.myapp.data.api.RetrofitProvider

object NewsRepositoryFactory {
    fun create(): NewsRepository {
        val retrofit = RetrofitProvider.provideRetrofit(BuildConfig.URL)

        val dataSource = when(BuildConfig.FLAVOR) {
            "newsapi" -> {
                val service = retrofit.create(NewsApi::class.java)
                NewsApiDataSource(service)
            }
            "newyorktimes" -> {
                val service = retrofit.create(NytApiService::class.java)
                NytApiDataSource(service)
            }
            else -> throw IllegalArgumentException("Unsupported Flavor: ${BuildConfig.FLAVOR}")
        }
        return NewsRepository(dataSource)
    }
}