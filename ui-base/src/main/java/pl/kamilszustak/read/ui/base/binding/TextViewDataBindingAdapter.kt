package pl.kamilszustak.read.ui.base.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.common.DateFormats
import java.util.*

object TextViewDataBindingAdapter {
    private const val DATE_ATTRIBUTE: String = "date"
    private const val DATE_TIME_ATTRIBUTE: String = "dateTime"

    @BindingAdapter(DATE_ATTRIBUTE)
    @JvmStatic
    fun TextView.setDate(date: Date?) {
        text = if (date != null) {
            DateFormats.dateFormat.format(date)
        } else {
            null
        }
    }

    @BindingAdapter(DATE_TIME_ATTRIBUTE)
    @JvmStatic
    fun TextView.setDateTime(date: Date?) {
        text = if (date != null) {
            DateFormats.dateTimeFormat.format(date)
        } else {
            null
        }
    }
}