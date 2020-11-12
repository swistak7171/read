package pl.kamilszustak.read.ui.main.collection.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class ReadingLogEvent : ViewEvent {
    data class OnLogEntryClicked(
        val logEntryId: LogEntryId,
    ) : ReadingLogEvent()
}