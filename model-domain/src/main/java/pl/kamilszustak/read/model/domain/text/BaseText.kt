package pl.kamilszustak.read.model.domain.text

import android.graphics.Point
import android.graphics.Rect
import android.os.Parcelable

interface BaseText : Parcelable {
    val language: String
    val value: String
    val boundingBox: Rect
    val cornerPoints: Array<Point>
    val components: List<BaseText>
}