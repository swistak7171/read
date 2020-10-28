package pl.kamilszustak.read.ui.main.collection

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class CollectionEvent : ViewEvent {
    object OnAddBookButtonClicked : CollectionEvent()
    object OnReadingLogButtonClicked : CollectionEvent()

    data class OnDialogOptionSelected(
        val index: Int,
    ) : CollectionEvent()

    data class OnUpdateReadingProgressButtonClicked(
        val bookId: BookId,
    ) : CollectionEvent()

    data class OnEditBookButtonClicked(
        val bookId: BookId,
    ) : CollectionEvent()

    data class OnDeleteBookButtonClicked(
        val bookId: BookId,
    ) : CollectionEvent()

    data class OnBookLongClicked(
        val bookId: BookId,
    ) : CollectionEvent()
}