package pl.kamilszustak.read.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Isbn(
    val type: IsbnType,
    val value: String,
) : Parcelable
