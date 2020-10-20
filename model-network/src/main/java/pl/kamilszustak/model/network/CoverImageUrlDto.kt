package pl.kamilszustak.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoverImageUrlDto(
    @Json(name = "thumbnail")
    val thumbnailUrl: String
)