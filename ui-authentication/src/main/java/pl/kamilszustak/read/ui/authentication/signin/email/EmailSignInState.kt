package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class EmailSignInState : ViewState {
    object Authenticated : EmailSignInState()

    data class Error(
        @StringRes val messageResourceId: Int
    ) : EmailSignInState()
}