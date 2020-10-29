package pl.kamilszustak.read.ui.main.book.details

import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class BookDetailsAction : ViewAction {
    data class NavigateToBookEditFragment(
        val bookId: BookId,
    ) : BookDetailsAction()

    data class NavigateToReadingProgressDialogFragment(
        val bookId: BookId,
    ) : BookDetailsAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : BookDetailsAction()

    object BookDeleted : BookDetailsAction()
    object NavigateUp : BookDetailsAction()
}
