package pl.kamilszustak.read.ui.main.book.progress

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class ReadingProgressState : ViewState {
    object NavigateUp : ReadingProgressState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : ReadingProgressState()
}
