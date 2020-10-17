package pl.kamilszustak.read.domain.access

import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateFormats @Inject constructor(
    private val getDefaultCountryCode: GetDefaultCountryCodeUseCase,
) {
    private val format: SimpleDateFormat by lazy {
        val code = getDefaultCountryCode() ?: "EN"
        val locale = Locale.forLanguageTag(code)
        SimpleDateFormat("", locale)
    }

    val timeFormat: SimpleDateFormat
        get() = getFormat("HH:mm")

    val dateFormat: SimpleDateFormat
        get() = getFormat("yyyy-MM-dd")

    val dateTimeFormat: SimpleDateFormat
        get() = getFormat("yyyy-MM-dd HH:mm")

    private fun getFormat(pattern: String): SimpleDateFormat = format.apply {
        this.applyPattern(pattern)
    }
}