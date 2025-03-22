package com.example.myapp.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.Article
import com.example.myapp.domain.model.ArticleWithDate
import com.example.myapp.domain.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.OffsetDateTime

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val TAG: String = "CHECK_RESPONSE"

    private var articlesListToOrder: List<ArticleWithDate> = emptyList()

    private var isFetched = false

    fun fetchNews() {
        if (isFetched) return
        isFetched = true

        _isLoading.value = true
        _errorMessage.value = null

        newsRepository.getTopHeadLines(
            country = "us",
            apiKey = "6444c6d8fdda4861aa9609eb13fe74b8",
            callback = object : Callback<TopHeadlinesResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<TopHeadlinesResponse>,
                    response: Response<TopHeadlinesResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        response.body()?.let { responseBody ->
                            Log.i(TAG, "onResponse: ${responseBody.articles}")
                            articlesListToOrder = responseBody.articles.map { article ->
                                ArticleWithDate(
                                    article = article,
                                    parsedDate = OffsetDateTime.parse(article.publishedAt)
                                )

                            }
                        }
                        _articles.value = articlesListToOrder
                            .sortedByDescending {
                                it.parsedDate
                            }
                            .map { item ->
                            Article(
                                source = item.article.source,
                                author = item.article.author,
                                title = item.article.title,
                                description = item.article.description,
                                url = item.article.url,
                                urlToImage = item.article.urlToImage,
                                publishedAt = item.article.publishedAt,
                                content = item.article.content
                            )
                        }
                    }
                    else {
                        _errorMessage.value = "Error: ${response.code()} - ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                    _isLoading.value = false
                    _errorMessage.value = "Failed to fetch news: ${t.message}"
                }
            }
        )
    }
}