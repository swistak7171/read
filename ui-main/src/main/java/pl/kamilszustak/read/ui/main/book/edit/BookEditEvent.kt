package pl.kamilszustak.read.ui.main.book.edit

import pl.kamilszustak.read.ui.base.view.ViewEvent
import java.util.*

internal sealed class BookEditEvent : ViewEvent {
    object OnDateEditTextClicked : BookEditEvent()
    object OnSaveBookButtonClicked : BookEditEvent()

    data class OnPublicationDateSelected(
        val date: Date,
    ) : BookEditEvent()
}