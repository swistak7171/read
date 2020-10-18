package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class MainMenuState : ViewState {
    object EmailAuthentication : MainMenuState()
    object PhoneAuthentication : MainMenuState()

    data class GoogleAuthentication(
        val intent: Intent,
    ) : MainMenuState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : MainMenuState()

    object Authenticated : MainMenuState()
}