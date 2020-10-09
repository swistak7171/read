package pl.kamilszustak.read.ui.main.book.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.State

sealed class BookEditState : State {
    data class Error(
        @StringRes val messageResourceId: Int,
    ) : BookEditState()

    object BookAdded : BookEditState()
}