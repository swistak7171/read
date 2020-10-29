package pl.kamilszustak.read.ui.main.collection

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class CollectionAction : ViewAction {
    data class ShowAddBookDialog(
        @ArrayRes val itemsResourceId: Int,
    ) : CollectionAction()

    data class NavigateToBookDetailsFragment(
        val bookId: BookId,
    ) : CollectionAction()

    data class NavigateToBookEditFragment(
        val bookId: BookId? = null,
    ) : CollectionAction()

    data class NavigateToReadingProgressDialogFragment(
        val bookId: BookId,
    ) : CollectionAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : CollectionAction()

    object BookDeleted : CollectionAction()
    object NavigateToSearchFragment : CollectionAction()
    object NavigateToReadingLogFragment : CollectionAction()
}