package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class QuoteEditState : ViewState {
    object NavigateUp : QuoteEditState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditState()

    data class QuoteSaved(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditState()
}