package pl.kamilszustak.read.ui.authentication.mainmenu

import com.facebook.login.LoginManager
import pl.kamilszustak.read.ui.base.view.State

sealed class MainMenuState : State {
    object EmailAuthentication : MainMenuState()
    object PhoneAuthentication : MainMenuState()
    object GoogleAuthentication : MainMenuState()

    data class FacebookAuthentication(
        val loginManager: LoginManager,
        val permissions: List<String>
    ) : MainMenuState()
}