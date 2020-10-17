package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.read.ui.base.view.ViewState

sealed class CollectionState : ViewState {
    object NavigateToBookEditFragment : CollectionState()
}