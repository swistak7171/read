package pl.kamilszustak.read.ui.authentication.signin.phone

import pl.kamilszustak.read.ui.base.view.Event

sealed class PhoneSignInEvent : Event {
    object OnSignInButtonClicked : PhoneSignInEvent()
}