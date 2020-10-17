package pl.kamilszustak.read.ui.main.quotes

import pl.kamilszustak.read.ui.base.view.ViewState

sealed class QuotesState : ViewState {
    object NavigateToQuoteEditFragment : QuotesState()
}