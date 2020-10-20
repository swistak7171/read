package pl.kamilszustak.read.ui.main.book.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class BookEditAction : ViewAction {
    object OpenDatePicker : BookEditAction()
    object NavigateUp : BookEditAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : BookEditAction()

    data class BookSaved(
        @StringRes val messageResourceId: Int,
    ) : BookEditAction()
}