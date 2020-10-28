package pl.kamilszustak.read.ui.main.collection.log.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.log.ObserveLogEntryUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.ui.base.view.ViewAction
import pl.kamilszustak.read.ui.base.view.ViewEvent
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel

class LogEntryDetailsViewModel(
    private val arguments: LogEntryDetailsFragmentArgs,
    private val observeLogEntry: ObserveLogEntryUseCase,
) : BaseViewModel<ViewEvent, ViewAction>() {

    val logEntry: LiveData<LogEntry> = observeLogEntry(LogEntryId(arguments.logEntryId))
        .asLiveData(viewModelScope.coroutineContext )

    override fun handleEvent(event: ViewEvent) {
    }
}