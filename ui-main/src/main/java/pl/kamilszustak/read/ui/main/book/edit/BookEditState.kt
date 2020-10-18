package pl.kamilszustak.read.ui.main.book.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class BookEditState : ViewState {
    object OpenDatePicker : BookEditState()
    object NavigateUp : BookEditState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : BookEditState()

    data class BookSaved(
        @StringRes val messageResourceId: Int,
    ) : BookEditState()
}