package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.State

sealed class PhoneSignInState : State {
    data class Error(
        @StringRes val messageResourceId: Int
    ) : PhoneSignInState()
}