package com.example.myapp.data.api.dto.news

import com.example.myapp.data.source.NewsApiArticleDto
import com.example.myapp.domain.model.Article
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsApiArticleDto>
)

@Serializable
data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class ArticleWithDate(
    val article: Article,
    val parsedDate: OffsetDateTime
)

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)