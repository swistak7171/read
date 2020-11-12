package pl.kamilszustak.read.model.domain.text

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class TextBaseWrapper(
    val text: String,
) : Parcelable