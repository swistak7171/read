package pl.kamilszustak.read.ui.base.binding

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.common.resource.ColorResource
import pl.kamilszustak.read.common.resource.DrawableResource
import pl.kamilszustak.read.ui.base.util.load

object ImageViewDataBindingAdapter {
    private const val IMAGE_URL_ATTRIBUTE: String = "imageUrl"
    private const val SRC_ATTRIBUTE: String = "android:src"
    private const val COLOR_DRAWABLE_ATTRIBUTE: String = "colorDrawable"

    @BindingAdapter(IMAGE_URL_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String?) {
        if (imageUrl != null) {
            load(imageUrl)
        }
    }

    @BindingAdapter(SRC_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setDrawableResource(resource: DrawableResource?) {
        val id = resource?.id
        if (id != null && id != 0) {
            setImageResource(id)
        } else {
            setImageDrawable(null)
        }
    }

    @BindingAdapter(SRC_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setColorResource(resource: ColorResource?) {
        val id = resource?.id
        if (id != null && id != 0) {
            val color = resource.get(context)
            setColorDrawable(color)
        } else {
            setColorDrawable(null)
        }
    }

    @BindingAdapter(COLOR_DRAWABLE_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setColorDrawable(@ColorInt value: Int?) {
        if (value != null && value != 0) {
            val colorDrawable = ColorDrawable(value)
            setImageDrawable(colorDrawable)
        } else {
            setImageDrawable(null)
        }
    }
}