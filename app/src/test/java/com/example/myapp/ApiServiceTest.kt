package com.example.myapp

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import com.example.myapp.data.api.MyApi
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.Article
import com.example.myapp.domain.model.Source
import com.example.myapp.domain.model.TopHeadlinesResponse
import com.example.myapp.presentation.viewmodel.NewsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Você prefere mockar o MyApi diretamente ou o NewsRepository?
//Como você garantiria que o callback do Retrofit está sendo chamado corretamente no teste?
//Você já utilizou Mockito.verify() para verificar se um método foi chamado?

class ApiServiceTest {

    @Mock
    private lateinit var mockApi: MyApi

    @Mock
    private lateinit var mockCall: Call<TopHeadlinesResponse>

    private lateinit var mockRepository: NewsRepository
    private lateinit var mockViewModel: NewsViewModel

    private val mockSource = Source(
        "10",
        "Teresa"
    )

    private val mockArticles = listOf(
        Article(
            source = mockSource,
            author = "Fernando",
            title = "Sample Title",
            description = "Sample Description",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg",
            publishedAt = "2024-02-04",
            content = "Sample Content"
        )
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockRepository = NewsRepository(mockApi)
        mockViewModel = NewsViewModel(mockRepository)
    }

    @SuppressLint("CheckResult")
    @Test
    fun `fetchData returns success when HTTP 200`() {
        val mockResponse = Response.success(
            TopHeadlinesResponse(
                status = "200",
                totalResults = mockArticles.size,
                articles = mockArticles
            )
        )

        val mockCall = mock(Call::class.java) as Call<TopHeadlinesResponse>

        doAnswer { invocation ->
            val callback = invocation.arguments[0] as Callback<TopHeadlinesResponse>
            callback.onResponse(mock(), Response.success(mock()))
            null
        }

        `when`(mockApi.getTopHeadLinesResponse("us", "apiKey")).thenReturn(mockCall)

        val observer = mock(Observer::class.java) as Observer<List<Article>>
        mockViewModel.articles
    }
}