package pl.kamilszustak.read.ui.main.book.edit

import pl.kamilszustak.read.ui.base.view.Event
import java.util.Date

sealed class BookEditEvent : Event {
    object OnDateEditTextClicked : BookEditEvent()
    object OnAddBookButtonClicked : BookEditEvent()

    data class OnPublicationDateSelected(
        val date: Date,
    ) : BookEditEvent()
}