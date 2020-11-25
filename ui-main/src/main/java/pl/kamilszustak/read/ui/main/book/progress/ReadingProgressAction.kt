package pl.kamilszustak.read.ui.main.book.progress

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class ReadingProgressAction : ViewAction {
    object ProgressUpdated : ReadingProgressAction()
    object NavigateUp : ReadingProgressAction()

    data class ChangePagesNumberPickerEnabled(
        val isEnabled: Boolean,
    ) : ReadingProgressAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : ReadingProgressAction()
}
