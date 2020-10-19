package pl.kamilszustak.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeDto(
    @Json(name = "id")
    val id: String,

    @Json(name = "volumeInfo")
    val volume: VolumeDetailsDto,
)