package pl.kamilszustak.read.ui.authentication.activity

import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class AuthenticationAction : ViewAction {
    object NavigateToMainActivity : AuthenticationAction()
}
