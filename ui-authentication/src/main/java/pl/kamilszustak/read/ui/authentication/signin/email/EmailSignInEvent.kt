package pl.kamilszustak.read.ui.authentication.signin.email

import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class EmailSignInEvent : ViewEvent {
    object OnSignInButtonClicked : EmailSignInEvent()
}