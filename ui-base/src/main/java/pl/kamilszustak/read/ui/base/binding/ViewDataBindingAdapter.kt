package pl.kamilszustak.read.ui.base.binding

import android.view.View
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.ui.base.util.setGone
import pl.kamilszustak.read.ui.base.util.setVisible

object ViewDataBindingAdapter {
    private const val VISIBLE_IF_NOT_NULL_ATTRIBUTE: String = "visibleIfNotNull"
    private const val GONE_IF_NULL_OR_BLANK_ATTRIBUTE: String = "goneIfNullOrBlank"
    private const val VISIBLE_IF_ATTRIBUTE: String = "visibleIf"

    @BindingAdapter(VISIBLE_IF_NOT_NULL_ATTRIBUTE)
    @JvmStatic
    fun View.setVisibleIfNotNull(value: Any?) {
        if (value != null) {
            setVisible()
        } else {
            setGone()
        }
    }

    @BindingAdapter(GONE_IF_NULL_OR_BLANK_ATTRIBUTE)
    @JvmStatic
    fun View.setGoneIfNullOrBlank(value: String?) {
        if (value.isNullOrBlank()) {
            setGone()
        } else {
            setVisible()
        }
    }

    @BindingAdapter(VISIBLE_IF_ATTRIBUTE)
    @JvmStatic
    fun View.setVisibleIf(isVisible: Boolean?) {
        visibility = if (isVisible == true) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}