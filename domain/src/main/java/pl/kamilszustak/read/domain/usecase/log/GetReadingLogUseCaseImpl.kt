package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.GetReadingLogUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import javax.inject.Inject

class GetReadingLogUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : GetReadingLogUseCase {

    override suspend fun invoke(): List<LogEntry> =
        repository.getAll()
            .map { mapper.map(it, Unit) }
}