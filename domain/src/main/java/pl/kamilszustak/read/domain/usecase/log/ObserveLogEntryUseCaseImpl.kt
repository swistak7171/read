package pl.kamilszustak.read.domain.usecase.log

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.ObserveLogEntryUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import javax.inject.Inject

class ObserveLogEntryUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : ObserveLogEntryUseCase {

    override fun invoke(input: LogEntryId): Flow<LogEntry> =
        repository.observeById(input.value)
            .map { mapper.map(it) }
}