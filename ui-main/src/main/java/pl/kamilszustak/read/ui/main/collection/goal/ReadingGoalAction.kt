package pl.kamilszustak.read.ui.main.collection.goal

import androidx.annotation.StringRes
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class ReadingGoalAction : ViewAction {
    data class ShowTimePicker(
        @StringRes val titleResourceId: Int,
        val time: Time,
    ) : ReadingGoalAction()
}