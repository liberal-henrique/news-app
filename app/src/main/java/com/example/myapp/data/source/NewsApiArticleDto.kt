package com.example.myapp.data.source

import kotlinx.serialization.Serializable


@Serializable
data class NewsApiArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)