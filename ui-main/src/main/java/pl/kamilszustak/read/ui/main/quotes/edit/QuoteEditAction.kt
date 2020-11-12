package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class QuoteEditAction : ViewAction {
    object NavigateUp : QuoteEditAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditAction()

    data class QuoteSaved(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditAction()
}