package com.example.myapp.domain.model
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime


@Serializable
data class Article(
    val id : String,
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    @Contextual val publishedAt: OffsetDateTime,
    val content: String?
)