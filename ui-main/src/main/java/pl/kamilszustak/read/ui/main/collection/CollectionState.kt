package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.read.ui.base.view.State

sealed class CollectionState : State {
    object NavigateToBookEditFragment : CollectionState()
}