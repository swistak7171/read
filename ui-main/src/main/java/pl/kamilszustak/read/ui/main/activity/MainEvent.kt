package pl.kamilszustak.read.ui.main.activity

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class MainEvent : ViewEvent {
    data class OnFragmentSelectionChanged(
        val type: MainFragmentType,
    ) : MainEvent()
}
