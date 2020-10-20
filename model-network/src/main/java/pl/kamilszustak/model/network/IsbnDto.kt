package pl.kamilszustak.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IsbnDto(
    @Json(name = "type")
    val type: String,

    @Json(name = "identifier")
    val value: String,
)