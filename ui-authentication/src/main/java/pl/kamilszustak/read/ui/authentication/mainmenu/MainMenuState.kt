package pl.kamilszustak.read.ui.authentication.mainmenu

import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import pl.kamilszustak.read.ui.base.view.State

sealed class MainMenuState : State {
    object EmailAuthentication : MainMenuState()
    object PhoneAuthentication : MainMenuState()

    data class GoogleAuthentication(
        val options: GoogleSignInOptions
    ) : MainMenuState()

    data class FacebookAuthentication(
        val loginManager: LoginManager,
        val permissions: List<String>
    ) : MainMenuState()
}