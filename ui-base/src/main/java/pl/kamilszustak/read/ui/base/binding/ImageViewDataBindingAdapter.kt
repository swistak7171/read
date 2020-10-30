package pl.kamilszustak.read.ui.base.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.common.resource.DrawableResource
import pl.kamilszustak.read.ui.base.util.load

object ImageViewDataBindingAdapter {
    private const val IMAGE_URL_ATTRIBUTE: String = "imageUrl"
    private const val SRC_ATTRIBUTE: String = "android:src"

    @BindingAdapter(IMAGE_URL_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String?) {
        if (imageUrl != null) {
            load(imageUrl)
        }
    }

    @BindingAdapter(SRC_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setDrawableResource(@DrawableRes drawableResource: Int?) {
        if (drawableResource != null && drawableResource != 0) {
            this.setImageResource(drawableResource)
        } else {
            this.setImageDrawable(null)
        }
    }

    @BindingAdapter(SRC_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setDrawableResource(resource: DrawableResource?) {
        this.setDrawableResource(resource?.id)
    }
}