package pl.kamilszustak.read.ui.main.collection.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.domain.access.usecase.log.ObserveReadingLogUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ReadingLogViewModel @Inject constructor(
    private val observeReadingLog: ObserveReadingLogUseCase,
) : BaseViewModel<ReadingLogEvent, ReadingLogAction>() {

    val readingLog: LiveData<List<LogEntry>> = observeReadingLog()
        .map { it.sortedByDescending(LogEntry::creationDate) }
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: ReadingLogEvent) {
        when (event) {
            is ReadingLogEvent.OnLogEntryClicked -> {
                _action.value = ReadingLogAction.NavigateToLogEntryDetailsFragment(event.logEntryId)
            }
        }
    }
}