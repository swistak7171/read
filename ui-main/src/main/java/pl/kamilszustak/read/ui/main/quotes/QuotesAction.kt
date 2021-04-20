package pl.kamilszustak.read.ui.main.quotes

import android.content.Intent
import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class QuotesAction : ViewAction {
    data class ShareQuote(
        val intent: Intent,
        @StringRes val titleResourceId: Int,
    ) : QuotesAction()

    data class NavigateToQuoteEditFragment(
        val quoteId: QuoteId? = null
    ) : QuotesAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuotesAction()

    object QuoteDeleted : QuotesAction()
}