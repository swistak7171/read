package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class CollectionEvent : ViewEvent {
    object OnAddBookButtonClicked : CollectionEvent()
}