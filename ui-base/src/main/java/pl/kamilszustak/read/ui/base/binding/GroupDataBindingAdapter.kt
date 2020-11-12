package pl.kamilszustak.read.ui.base.binding

import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter
import timber.log.Timber

object GroupDataBindingAdapter {
    private const val VISIBLE_IF_ATTRIBUTE: String = "visibleIf"

    @BindingAdapter(VISIBLE_IF_ATTRIBUTE)
    @JvmStatic
    fun Group.setVisibleIf(isVisible: Boolean?) {
        Timber.i("COLLECTION $isVisible")
        visibility = if (isVisible == true) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}