package pl.kamilszustak.read.ui.main.book.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class BookEditState : ViewState {
    data class Error(
        @StringRes val messageResourceId: Int,
    ) : BookEditState()

    object OpenDatePicker : BookEditState()
    object BookAdded : BookEditState()
}