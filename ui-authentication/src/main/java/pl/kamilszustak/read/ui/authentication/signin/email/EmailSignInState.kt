package pl.kamilszustak.read.ui.authentication.signin.email

import pl.kamilszustak.read.ui.base.view.State

sealed class EmailSignInState : State {
    object Authenticated : EmailSignInState()
}