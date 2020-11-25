package pl.kamilszustak.read.ui.main.book.progress

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ReadingProgressEvent : ViewEvent {
    data class OnFinishCheckBoxCheckedChanged(
        val isChecked: Boolean,
    ) : ReadingProgressEvent()

    object OnCancelButtonClicked : ReadingProgressEvent()
    object OnSaveButtonClicked : ReadingProgressEvent()
}
