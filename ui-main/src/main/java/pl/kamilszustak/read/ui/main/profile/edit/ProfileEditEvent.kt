package pl.kamilszustak.read.ui.main.profile.edit

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ProfileEditEvent : ViewEvent {
    object OnSaveButtonClicked : ProfileEditEvent()
}