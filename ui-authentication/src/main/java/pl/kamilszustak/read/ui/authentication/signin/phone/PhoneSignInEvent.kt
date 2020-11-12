package pl.kamilszustak.read.ui.authentication.signin.phone

import android.app.Activity
import pl.kamilszustak.read.ui.base.view.ViewEvent
import java.lang.ref.WeakReference

internal sealed class PhoneSignInEvent : ViewEvent {
    object OnCountryEditTextClicked : PhoneSignInEvent()
    object OnVerificationCodeButtonClicked : PhoneSignInEvent()

    data class OnSignInButtonClicked(
        val activityReference: WeakReference<out Activity>,
    ) : PhoneSignInEvent()

    data class OnCountrySelected(
        val index: Int,
    ) : PhoneSignInEvent()

    data class OnVerificationCodeEntered(
        val code: String,
    ) : PhoneSignInEvent()
}