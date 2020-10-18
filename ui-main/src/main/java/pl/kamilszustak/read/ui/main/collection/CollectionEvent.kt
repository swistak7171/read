package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class CollectionEvent : ViewEvent {
    object OnAddBookButtonClicked : CollectionEvent()

    data class OnBookMenuButtonClicked(
        val collectionBookId: CollectionBookId,
    ) : CollectionEvent()

    data class OnBookLongClicked(
        val collectionBookId: CollectionBookId,
    ) : CollectionEvent()
}