package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class QuoteEditState : ViewState {
    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditState()

    object QuoteAdded : QuoteEditState()
}