package com.example.myapp.data.mapper


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.data.api.dto.news.TopHeadlinesResponse
import com.example.myapp.domain.model.Article
import java.time.OffsetDateTime

import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
fun TopHeadlinesResponse.toArticles(): List<Article> {
    return articles.map {
        Article(
            id = UUID.randomUUID().toString(),
            sourceName = it.source.name,
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = OffsetDateTime.parse(it.publishedAt),
            content = it.content
        )
    }
}
