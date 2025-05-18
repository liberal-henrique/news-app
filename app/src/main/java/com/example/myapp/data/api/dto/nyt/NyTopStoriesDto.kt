package com.example.myapp.data.api.dto.nyt

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Serializable
data class NytArchiveResponse(
    val response: NytArchiveDocsWrapper
)

@Serializable
data class NytArchiveDocsWrapper(
    val docs: List<NytArticle>
)

@Serializable
data class NytArticle(
    val abstract: String,
    @SerialName("web_url")
    val url: String,
    @SerialName("pub_date")
    val publishedDate: String,
    val source: String,
    val byline: NytByline? = null,
    val headline: NytHeadline,
    val adx_keywords: String? = null,
    val media: List<NytMedia> = emptyList()
)

@Serializable
data class NytHeadline(
    val main: String
)

@Serializable
data class NytByline(
    val original: String? = null
)

@RequiresApi(Build.VERSION_CODES.O)
fun NytArticle.toArticle(): Article {
    val imageUrl = media
        .firstOrNull { it.type == "image" }
        ?.mediaMetadata
        ?.firstOrNull()
        ?.url
        ?.let { "https://www.nytimes.com/$it" }

    return Article(
        id = UUID.randomUUID().toString(),
        sourceName = this.source,
        author = this.byline?.original,
        title = this.headline.main,
        description = this.abstract,
        url = this.url,
        urlToImage = imageUrl,
        publishedAt = OffsetDateTime.parse(this.publishedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")),
        content = this.adx_keywords
    )
}

@Serializable
data class NytMedia(
    val type: String,
    val subtype: String,
    @SerialName("media-metadata")
    val mediaMetadata: List<NytMediaMetadata>
)

@Serializable
data class NytMediaMetadata(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
)