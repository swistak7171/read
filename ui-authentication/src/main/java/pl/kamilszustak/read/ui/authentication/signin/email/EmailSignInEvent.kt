package pl.kamilszustak.read.ui.authentication.signin.email

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class EmailSignInEvent : ViewEvent {
    object OnSignInButtonClicked : EmailSignInEvent()
}