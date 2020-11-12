package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class MainMenuAction : ViewAction {
    object EmailAuthentication : MainMenuAction()
    object PhoneAuthentication : MainMenuAction()

    data class GoogleAuthentication(
        val intent: Intent,
    ) : MainMenuAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : MainMenuAction()

    object Authenticated : MainMenuAction()
}