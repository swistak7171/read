package pl.kamilszustak.read.ui.authentication.mainmenu

import android.app.Activity
import android.content.Intent
import pl.kamilszustak.read.ui.base.view.Event
import java.lang.ref.WeakReference

sealed class MainMenuEvent : Event {
    object OnEmailSignInButtonClicked : MainMenuEvent()
    object OnPhoneSignInButtonClicked : MainMenuEvent()

    data class OnGoogleSignInButtonClicked(
        val webClientId: String
    ) : MainMenuEvent()

    data class OnActivityGoogleResult(
        val intent: Intent
    ) : MainMenuEvent()

    object OnFacebookSignInButtonClicked : MainMenuEvent()

    data class OnActivityFacebookResult(
        val requestCode: Int,
        val resultCode: Int,
        val data: Intent?
    ) : MainMenuEvent()

    data class OnTwitterSignInButtonClicked(
        val activityReference: WeakReference<Activity>
    ) : MainMenuEvent()
}