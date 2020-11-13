package pl.kamilszustak.read.ui.main.scanner.selection

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Region
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.ColorInt

class HighlightableBitmap(
    resources: Resources,
    bitmap: Bitmap,
    @ColorInt private val color: Int,
) : BitmapDrawable(resources, bitmap) {

    private var selection: RectF? = null

    fun selectRegion(region: RectF?) {
        selection = region
    }

    fun selectRegion(left: Float, top: Float, right: Float, bottom: Float) {
        val rectangle = RectF(left, top, right, bottom)
        selectRegion(rectangle)
    }

    fun clearSelection() {
        selectRegion(null)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        selection?.let {
            canvas.clipRect(it, Region.Op.DIFFERENCE)
            canvas.drawColor(color)
        }
    }
}