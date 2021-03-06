package pl.kamilszustak.read.ui.main.scanner.selection

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Region
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.ColorInt
import kotlin.math.abs

class HighlightableBitmap(
    resources: Resources,
    bitmap: Bitmap,
    @ColorInt private val darkenColor: Int,
) : BitmapDrawable(resources, bitmap) {

    private var selection: RectF? = null

    fun selectArea(area: RectF?) {
        selection = area
        invalidateSelf()
    }

    fun selectArea(left: Float, top: Float, right: Float, bottom: Float) {
        val rectangle = RectF(left, top, right, bottom)
        selectArea(rectangle)
    }

    fun clearSelection() {
        selectArea(null)
    }

    fun getSelectedBitmap(): Bitmap {
        val selection = this.selection

        return if (selection != null) {
            var x = selection.left.toInt()
            if (x < 0) {
                x = 0
            }

            var y = selection.top.toInt()
            if (y < 0) {
                y = 0
            }

            val width = abs(x - selection.right).toInt()
            val height = abs(y - selection.bottom).toInt()

            Bitmap.createBitmap(bitmap, x, y, width, height)
        } else {
            bitmap
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        selection?.let {
            canvas.clipRect(it, Region.Op.DIFFERENCE)
            canvas.drawColor(darkenColor)
        }
    }
}