package pl.kamilszustak.read.ui.authentication.signin.phone

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class PhoneSignInEvent : ViewEvent {
    object OnCountryEditTextClicked : PhoneSignInEvent()
    object OnSignInButtonClicked : PhoneSignInEvent()

    data class OnCountrySelected(
        val index: Int,
    ) : PhoneSignInEvent()
}