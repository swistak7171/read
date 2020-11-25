package pl.kamilszustak.read.ui.main.collection.goal

import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ReadingGoalEvent : ViewEvent {
    object OnTimeEditTextClicked : ReadingGoalEvent()
    object OnTimeClearButtonClicked : ReadingGoalEvent()
    object OnSaveButtonClicked : ReadingGoalEvent()

    data class OnTimeSelected(
        val time: Time,
    ) : ReadingGoalEvent()
}
