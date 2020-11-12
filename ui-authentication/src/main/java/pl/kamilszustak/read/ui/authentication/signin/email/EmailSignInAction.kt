package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class EmailSignInAction : ViewAction {
    object Authenticated : EmailSignInAction()

    data class Error(
        @StringRes val messageResourceId: Int
    ) : EmailSignInAction()
}