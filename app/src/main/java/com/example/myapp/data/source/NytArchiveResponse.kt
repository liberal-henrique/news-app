package com.example.myapp.data.source

import kotlinx.serialization.Serializable

@Serializable
data class NytArchiveResponse(
    val response: NytArchiveDocsWrapper
)

@Serializable
data class NytArchiveDocsWrapper(
    val docs: List<NytArticleDto> // ✅ NÃO use List<Article>
)

@Serializable
data class NytArticleDto(
    val abstract: String,
    val web_url: String,
    val snippet: String,
    val source: String,
    val pub_date: String,
    val byline: BylineDto? = null,
    val multimedia: List<MultimediaDto> = emptyList(),
    val headline: HeadlineDto
)

@Serializable
data class BylineDto(
    val original: String? = null
)

@Serializable
data class HeadlineDto(
    val main: String
)

@Serializable
data class MultimediaDto(
    val url: String,
    val subtype: String
)