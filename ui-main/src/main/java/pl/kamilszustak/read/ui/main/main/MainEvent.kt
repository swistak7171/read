package pl.kamilszustak.read.ui.main.main

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class MainEvent : ViewEvent {
    data class OnFragmentSelectionChanged(
        val type: MainFragmentType,
    ) : MainEvent()
}
