package pl.kamilszustak.read.ui.main.quotes.edit

import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class QuoteEditEvent : ViewEvent {
    data class OnColorSelected(
        val index: Int,
    ): QuoteEditEvent()

    object OnSaveQuoteButtonClicked : QuoteEditEvent()
}