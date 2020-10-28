package pl.kamilszustak.read.ui.main.collection.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class ReadingLogAction : ViewAction {
    data class NavigateToLogEntryDetailsFragment(
        val logEntryId: LogEntryId,
    ) : ReadingLogAction()
}
