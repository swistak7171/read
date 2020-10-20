package pl.kamilszustak.read.ui.main.search

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class SearchEvent : ViewEvent {
    object OnSearchButtonClicked : SearchEvent()
}