package pl.kamilszustak.read.model.domain.text

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextWrapper(
    val blocks: List<TextBlock>,
) : Parcelable {

    val text: String
        get() = buildString {
            blocks.forEach { block ->
                append(System.lineSeparator())
                append(block.value)
            }
        }
}