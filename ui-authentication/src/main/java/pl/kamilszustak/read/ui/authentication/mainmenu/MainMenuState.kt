package pl.kamilszustak.read.ui.authentication.mainmenu

import pl.kamilszustak.read.ui.base.view.State

sealed class MainMenuState : State {
    object EmailAuthentication : MainMenuState()
    object PhoneAuthentication : MainMenuState()
}