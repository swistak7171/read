package pl.kamilszustak.read.ui.base.binding

import android.widget.NumberPicker
import androidx.databinding.BindingAdapter

object NumberPickerDataBindingAdapter {
    private const val MINIMAL_VALUE_ATTRIBUTE: String = "min"
    private const val MAXIMAL_VALUE_ATTRIBUTE: String = "max"
    private const val WRAP_SELECTOR_WHEEL_ATTRIBUTE: String = "wrapSelectorWheel"

    @BindingAdapter(MINIMAL_VALUE_ATTRIBUTE)
    @JvmStatic
    fun NumberPicker.setMinimalValue(value: Int?) {
        if (value != null) {
            minValue = value
        }
    }

    @BindingAdapter(MAXIMAL_VALUE_ATTRIBUTE)
    @JvmStatic
    fun NumberPicker.setMaximalValue(value: Int?) {
        if (value != null) {
            maxValue = value
        }
    }

    @BindingAdapter(WRAP_SELECTOR_WHEEL_ATTRIBUTE)
    @JvmStatic
    fun NumberPicker.setWrapSelectorWheel(value: Boolean?) {
        if (value != null) {
            wrapSelectorWheel = value
        }
    }
}