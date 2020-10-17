package pl.kamilszustak.read.ui.main.quotes.edit

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class QuoteEditEvent : ViewEvent {
    object OnAddQuoteButtonClicked : QuoteEditEvent()
}