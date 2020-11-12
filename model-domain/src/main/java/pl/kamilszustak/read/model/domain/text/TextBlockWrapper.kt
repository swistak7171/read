package pl.kamilszustak.read.model.domain.text

import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextBlockWrapper(
    val block: TextBlock,
) : BaseTextWrapper(block)