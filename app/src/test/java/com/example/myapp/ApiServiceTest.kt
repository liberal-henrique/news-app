package com.example.myapp

import com.example.myapp.data.api.MyApi
import com.example.myapp.data.repository.NewsRepository
import com.example.myapp.domain.model.TopHeadlinesResponse
import com.example.myapp.presentation.viewmodel.NewsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call

//Perguntas para você refletir antes de codar
//
//Você prefere mockar o MyApi diretamente ou o NewsRepository?
//Como você garantiria que o callback do Retrofit está sendo chamado corretamente no teste?
//Você já utilizou Mockito.verify() para verificar se um método foi chamado?

class ApiServiceTest {
    @Before
    fun setUp() {

    }

    @Test
    fun `fetchData returns success when HTTP 200`() {
        val mockApi = mock(MyApi::class.java)
        val mockRepository = NewsRepository(mockApi)
        val viewModel = NewsViewModel(mockRepository)
        val mockCall = mock(Call::class.java) as Call<*>


    @Test
    fun `fetchData returns failure when HTTP 400`() {

    }

    @Test
    fun `fetchData returns failure when HTTP 500`() {

    }

    @Test
    fun `fetchData returns failure when connection error occurs`() {

    }
}