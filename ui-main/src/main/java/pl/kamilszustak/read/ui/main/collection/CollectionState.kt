package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class CollectionState : ViewState {
    object NavigateToBookEditFragment : CollectionState()

    data class NavigateToReadingProgressDialogFragment(
        val collectionBookId: CollectionBookId,
    ) : CollectionState()
}