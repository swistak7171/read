package pl.kamilszustak.read.ui.authentication.signin.email

import pl.kamilszustak.read.ui.base.view.Event

sealed class EmailSignInEvent : Event {
    object OnSignInButtonClicked : EmailSignInEvent()
}