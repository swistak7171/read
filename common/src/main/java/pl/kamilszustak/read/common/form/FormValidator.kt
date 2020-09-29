package pl.kamilszustak.read.common.form

import android.util.Patterns
import javax.inject.Inject

class FormValidator @Inject constructor() {
    fun validateEmailAddress(text: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(text).matches()
}