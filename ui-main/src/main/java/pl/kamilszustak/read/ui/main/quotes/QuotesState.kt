package pl.kamilszustak.read.ui.main.quotes

import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class QuotesState : ViewState {
    data class NavigateToQuoteEditFragment(
        val quoteId: QuoteId? = null
    ) : QuotesState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuotesState()

    object QuoteDeleted : QuotesState()
}