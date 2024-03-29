package pl.kamilszustak.read.ui.main.quotes

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class QuotesEvent : ViewEvent {
    object OnAddQuoteButtonClicked : QuotesEvent()

    data class OnShareQuoteButtonClicked(
        val quoteId: QuoteId,
    ) : QuotesEvent()

    data class OnDeleteQuoteButtonClicked(
        val quoteId: QuoteId,
    ) : QuotesEvent()

    data class OnEditQuoteButtonClicked(
        val quoteId: QuoteId,
    ) : QuotesEvent()
}