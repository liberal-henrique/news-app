package com.example.myapp.data.source

import com.example.myapp.domain.model.Article

interface NewsDataSource {
    fun getTopHeadlines(callback: (Result<List<Article>>) -> Unit)
}