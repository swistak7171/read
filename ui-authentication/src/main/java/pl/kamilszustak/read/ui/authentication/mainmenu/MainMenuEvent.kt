package pl.kamilszustak.read.ui.authentication.mainmenu

import pl.kamilszustak.read.ui.base.view.Event

sealed class MainMenuEvent : Event {
    object OnEmailSignInButtonClicked : MainMenuEvent()
    object OnPhoneSignInButtonClicked : MainMenuEvent()
}