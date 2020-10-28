package pl.kamilszustak.read.ui.main.collection.log.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.log.ObserveLogEntryUseCase

class LogEntryDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: LogEntryDetailsFragmentArgs,
    private val observeLogEntryUseCase: ObserveLogEntryUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LogEntryDetailsViewModel(
            arguments = arguments,
            observeLogEntry = observeLogEntryUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: LogEntryDetailsFragmentArgs): LogEntryDetailsViewModelFactory
    }
}