package com.example.myapp.domain.model

import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

@Serializable
data class Article(
    val source: Source,
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
data class Source(
    val id: String?,
    val name: String
)