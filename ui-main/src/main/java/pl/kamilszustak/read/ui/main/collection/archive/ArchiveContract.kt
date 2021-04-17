package pl.kamilszustak.read.ui.main.collection.archive

import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.ui.base.view.ViewAction
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ArchiveEvent : ViewEvent {
    data class OnUnarchiveBookButtonClicked(
        val id: BookId,
    ) : ArchiveEvent()
}

sealed class ArchiveAction : ViewAction {
    data class ShowSuccessToast(
        @StringRes val messageResourceId: Int,
    ) : ArchiveAction()

    data class ShowErrorToast(
        @StringRes val messageResourceId: Int,
    ) : ArchiveAction()
}