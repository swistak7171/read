package pl.kamilszustak.read.ui.base.binding

import android.widget.NumberPicker
import androidx.databinding.BindingAdapter

object NumberPickerDataBindingAdapter {
    private const val MINIMAL_VALUE_ATTRIBUTE: String = "min"
    private const val MAXIMAL_VALUE_ATTRIBUTE: String = "max"

    @BindingAdapter(MINIMAL_VALUE_ATTRIBUTE)
    @JvmStatic
    fun NumberPicker.setMinimalValue(value: Int?) {
        if (value != null) {
            this.minValue = value
        }
    }

    @BindingAdapter(MAXIMAL_VALUE_ATTRIBUTE)
    @JvmStatic
    fun NumberPicker.setMaximalValue(value: Int?) {
        if (value != null) {
            this.maxValue = value
        }
    }
}