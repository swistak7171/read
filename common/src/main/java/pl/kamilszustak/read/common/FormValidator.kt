package pl.kamilszustak.read.common

import android.util.Patterns
import androidx.core.text.isDigitsOnly
import javax.inject.Inject

class FormValidator @Inject constructor() {
    fun validateEmailAddress(text: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(text).matches()

    fun validatePhoneNumber(text: String): Boolean =
        Patterns.PHONE.matcher(text).matches()

    fun validateNumericText(text: String): Boolean =
        text.isDigitsOnly()
}