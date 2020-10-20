package pl.kamilszustak.model.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeListWrapper(
    @Json(name = "totalItems")
    val volumesNumber: Int,

    @Json(name = "items")
    val volumes: List<VolumeDto>,
)