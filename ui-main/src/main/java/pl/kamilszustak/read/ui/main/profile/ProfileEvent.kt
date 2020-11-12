package pl.kamilszustak.read.ui.main.profile

import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class ProfileEvent : ViewEvent {
    object OnSignOutButtonClicked : ProfileEvent()
}