package pl.kamilszustak.read.ui.base.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import pl.kamilszustak.read.common.util.useOrNull

object EditTextDataBindingAdapter {
    private const val TEXT_ATTRIBUTE: String = "android:text"

    @BindingAdapter(TEXT_ATTRIBUTE)
    @JvmStatic
    fun EditText.setInt(value: Int?) {
        val text = value?.toString() ?: ""
        this.setText(text)
        this.text.useOrNull {
            this.setSelection(it.length)
        }
    }

    @InverseBindingAdapter(attribute = TEXT_ATTRIBUTE)
    @JvmStatic
    fun EditText.getInt(): Int? =
        this.text.toString().toIntOrNull()
}