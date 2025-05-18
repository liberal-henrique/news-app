package com.example.myapp.data.source

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.BuildConfig
import com.example.myapp.data.api.NytApiService
import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import com.example.myapp.data.api.dto.nyt.NytArchiveResponse
import com.example.myapp.data.api.dto.nyt.toArticle
import com.example.myapp.domain.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NytApiDataSource(
    private val service: NytApiService
): NewsDataSource {

    override fun getTopHeadlines(callback: (Result<List<Article>>) -> Unit) {
        service.getTopStories(
            year = 2024,
            month = 1,
            apiKey = BuildConfig.NEWS_API_KEY
        ).enqueue(object : Callback<NytArchiveResponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<NytArchiveResponse>,
                response: Response<NytArchiveResponse>
            ) {
                if (response.isSuccessful) {
                    val articles = response.body()?.response?.docs?.map {
                        it.toArticle()
                    } ?: emptyList()
                    callback(Result.success(articles))
                }
                else {
                    callback(Result.failure(Throwable("Erro NYT: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<NytArchiveResponse>, t: Throwable) {
                callback(Result.failure(t))
            }

        })
    }
}