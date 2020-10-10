package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.State

sealed class QuoteEditState : State {
    data class Error(
        @StringRes val messageResourceId: Int,
    ) : QuoteEditState()
}