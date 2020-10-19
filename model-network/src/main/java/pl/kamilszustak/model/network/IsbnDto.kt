package pl.kamilszustak.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IsbnDto(
    val type: String,
    val value: String,
)