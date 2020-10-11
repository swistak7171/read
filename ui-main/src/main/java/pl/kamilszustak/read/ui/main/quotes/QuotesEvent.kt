package pl.kamilszustak.read.ui.main.quotes

import pl.kamilszustak.read.ui.base.view.Event

sealed class QuotesEvent : Event {
    object OnAddQuoteButtonClicked : QuotesEvent()
}