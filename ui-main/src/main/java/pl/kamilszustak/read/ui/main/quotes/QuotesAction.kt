package pl.kamilszustak.read.ui.main.quotes

import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class QuotesAction : ViewAction {
    data class NavigateToQuoteEditFragment(
        val quoteId: QuoteId? = null
    ) : QuotesAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuotesAction()

    object QuoteDeleted : QuotesAction()
}