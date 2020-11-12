package pl.kamilszustak.read.ui.main.book.progress

import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class ReadingProgressEvent : ViewEvent {
    object OnCancelButtonClicked : ReadingProgressEvent()
    object OnSaveButtonClicked : ReadingProgressEvent()
}
