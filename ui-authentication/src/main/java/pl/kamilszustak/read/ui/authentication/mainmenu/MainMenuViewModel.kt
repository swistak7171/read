package pl.kamilszustak.read.ui.authentication.mainmenu

import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(): BaseViewModel<MainMenuEvent, MainMenuState>() {
    override fun handleEvent(event: MainMenuEvent) {
        _state.value = when (event) {
            MainMenuEvent.OnEmailSignInButtonClicked -> MainMenuState.EmailAuthentication
            MainMenuEvent.OnPhoneSignInButtonClicked -> MainMenuState.PhoneAuthentication
        }
    }
}