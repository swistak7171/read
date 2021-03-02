package pl.kamilszustak.read.ui.base.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.kamilszustak.read.common.date.DateFormats
import java.util.*

object TextViewDataBindingAdapter {
    private const val DATE_ATTRIBUTE: String = "date"
    private const val DATE_TIME_ATTRIBUTE: String = "dateTime"

    @BindingAdapter(DATE_ATTRIBUTE)
    @JvmStatic
    fun TextView.setDate(date: Date?) {
        text = date?.let {
            DateFormats.dateFormat.format(it)
        }
    }

    @BindingAdapter(DATE_TIME_ATTRIBUTE)
    @JvmStatic
    fun TextView.setDateTime(date: Date?) {
        text = date?.let {
            DateFormats.dateTimeFormat.format(it)
        }
    }
}