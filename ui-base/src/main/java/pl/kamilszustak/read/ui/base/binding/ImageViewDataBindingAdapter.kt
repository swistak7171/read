package pl.kamilszustak.read.ui.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.ui.base.util.load

object ImageViewDataBindingAdapter {
    private const val IMAGE_URL_ATTRIBUTE: String = "imageUrl"

    @BindingAdapter(IMAGE_URL_ATTRIBUTE)
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String?) {
        if (imageUrl != null) {
            load(imageUrl)
        }
    }
}