package pl.kamilszustak.read.ui.main.quotes

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class QuotesEvent : ViewEvent {
    object OnAddQuoteButtonClicked : QuotesEvent()
}