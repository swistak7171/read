package pl.kamilszustak.read.ui.main.book.edit

import pl.kamilszustak.read.ui.base.view.ViewEvent
import java.util.*

sealed class BookEditEvent : ViewEvent {
    object OnDateEditTextClicked : BookEditEvent()
    object OnDateClearButtonClicked : BookEditEvent()
    object OnSaveBookButtonClicked : BookEditEvent()

    data class OnPublicationDateSelected(
        val date: Date,
    ) : BookEditEvent()
}