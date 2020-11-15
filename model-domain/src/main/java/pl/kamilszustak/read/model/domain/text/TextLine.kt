package pl.kamilszustak.read.model.domain.text

import android.graphics.Point
import android.graphics.Rect
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextLine(
    override val language: String,
    override val value: String,
    override val boundingBox: Rect,
    override val cornerPoints: Array<Point>,
    override val components: List<TextElement>,
) : BaseText {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextLine

        if (language != other.language) return false
        if (value != other.value) return false
        if (boundingBox != other.boundingBox) return false
        if (!cornerPoints.contentEquals(other.cornerPoints)) return false
        if (components != other.components) return false

        return true
    }

    override fun hashCode(): Int {
        var result = language.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + boundingBox.hashCode()
        result = 31 * result + cornerPoints.contentHashCode()
        result = 31 * result + components.hashCode()
        return result
    }
}