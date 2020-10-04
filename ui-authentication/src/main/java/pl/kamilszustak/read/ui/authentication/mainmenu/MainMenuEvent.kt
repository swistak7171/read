package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import pl.kamilszustak.read.ui.base.view.Event

sealed class MainMenuEvent : Event {
    object OnEmailSignInButtonClicked : MainMenuEvent()
    object OnPhoneSignInButtonClicked : MainMenuEvent()

    data class OnGoogleSignInButtonClicked(
        val webClientId: String
    ) : MainMenuEvent()

    object OnFacebookSignInButtonClicked : MainMenuEvent()

    data class OnActivityResult(
        val requestCode: Int,
        val resultCode: Int,
        val data: Intent?
    ) : MainMenuEvent()
}