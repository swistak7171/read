package pl.kamilszustak.read.model.domain.text

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TextElementWrapper(
    val element: TextElement,
) : BaseTextWrapper(element), Parcelable