package pl.kamilszustak.read.model.domain.text

import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextLineWrapper(
    val line: TextLine,
) : BaseTextWrapper(line)