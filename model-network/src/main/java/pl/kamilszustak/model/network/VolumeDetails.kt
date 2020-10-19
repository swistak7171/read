package pl.kamilszustak.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeDetails(
    @Json(name = "title")
    val title: String,

    @Json(name = "subtitle")
    val subtitle: String,

    @Json(name = "authors")
    val authors: List<String>,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "publishedDate")
    val publicationDate: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "pageCount")
    val pagesNumber: Int,

    @Json(name = "imageLinks")
    val coverImageUrl: CoverImageUrlDto
)