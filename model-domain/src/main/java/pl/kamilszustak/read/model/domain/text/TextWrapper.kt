package pl.kamilszustak.read.model.domain.text

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextWrapper(
    val blocks: List<TextBlock>,
) : Parcelable {

    val value: String
        get() = buildString {
            blocks.forEach { block ->
                append(block.value)
                append(System.lineSeparator())
            }
            trim()
        }
}