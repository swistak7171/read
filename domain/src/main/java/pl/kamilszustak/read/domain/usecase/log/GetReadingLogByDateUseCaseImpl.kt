package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.GetReadingLogByDateUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetReadingLogByDateUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : GetReadingLogByDateUseCase {

    override suspend fun invoke(input: Date): List<LogEntry> {
        val entries = repository.getAllByDate(input)

        return mapper.mapAll(entries, Unit)
    }
}