package pl.kamilszustak.read.domain.access

import java.text.SimpleDateFormat
import java.util.*

object DateFormats {
    private val format: SimpleDateFormat by lazy {
        val locale = Locale.getDefault()
        SimpleDateFormat("", locale)
    }

    @JvmStatic
    val timeFormat: SimpleDateFormat
        get() = getFormat("HH:mm")

    @JvmStatic
    val yearFormat: SimpleDateFormat
        get() = getFormat("yyyy")

    @JvmStatic
    val yearMonthFormat: SimpleDateFormat
        get() = getFormat("yyyy-MM")

    @JvmStatic
    val dateFormat: SimpleDateFormat
        get() = getFormat("yyyy-MM-dd")

    @JvmStatic
    val dateTimeFormat: SimpleDateFormat
        get() = getFormat("yyyy-MM-dd HH:mm")

    private fun getFormat(pattern: String): SimpleDateFormat = format.apply {
        this.applyPattern(pattern)
    }
}