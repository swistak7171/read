package pl.kamilszustak.read.ui.splashscreen.activity

import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class SplashScreenAction : ViewAction {
    object NavigateToAuthenticationActivity : SplashScreenAction()
    object NavigateToMainActivity : SplashScreenAction()
}
