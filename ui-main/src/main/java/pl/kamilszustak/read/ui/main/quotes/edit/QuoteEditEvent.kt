package pl.kamilszustak.read.ui.main.quotes.edit

import pl.kamilszustak.read.ui.base.view.Event

sealed class QuoteEditEvent : Event {
    object OnAddQuoteButtonClicked : QuoteEditEvent()
}