package pl.kamilszustak.read.model.domain.text

import android.graphics.Point
import android.graphics.Rect
import android.os.Parcelable

abstract class BaseTextWrapper(
    text: BaseText,
) : BaseText, Parcelable {

    override val language: String = text.language
    override val value: String = text.value
    override val boundingBox: Rect = text.boundingBox
    override val cornerPoints: Array<Point> = text.cornerPoints
    override val components: List<BaseText> = text.components
}