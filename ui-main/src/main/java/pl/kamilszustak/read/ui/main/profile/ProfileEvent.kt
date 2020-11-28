package pl.kamilszustak.read.ui.main.profile

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ProfileEvent : ViewEvent {
    object OnEditButtonClicked : ProfileEvent()
    object OnMoreStatisticsButtonClicked : ProfileEvent()
    object OnSignOutButtonClicked : ProfileEvent()
}