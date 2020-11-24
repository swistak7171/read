package pl.kamilszustak.read.domain.usecase.log

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.ObserveReadingLogUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import javax.inject.Inject

class ObserveReadingLogUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : ObserveReadingLogUseCase {

    override fun invoke(): Flow<List<LogEntry>> =
        repository.observeAll()
            .map { mapper.mapAll(it, Unit) }
}