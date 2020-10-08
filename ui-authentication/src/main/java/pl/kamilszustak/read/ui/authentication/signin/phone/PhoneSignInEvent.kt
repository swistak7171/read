package pl.kamilszustak.read.ui.authentication.signin.phone

import pl.kamilszustak.read.ui.base.view.Event

sealed class PhoneSignInEvent : Event {
    object OnCountryEditTextClicked : PhoneSignInEvent()
    data class OnCountrySelected(
        val index: Int
    ) : PhoneSignInEvent()
    object OnSignInButtonClicked : PhoneSignInEvent()
}