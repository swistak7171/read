package pl.kamilszustak.read.ui.main.book.details

import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class BookDetailsEvent : ViewEvent {
    object OnEditBookButtonClicked : BookDetailsEvent()
    object OnDeleteBookButtonClicked : BookDetailsEvent()
    object OnUpdateReadingProgressButtonClicked : BookDetailsEvent()
    object OnProgressBarClicked : BookDetailsEvent()
}
