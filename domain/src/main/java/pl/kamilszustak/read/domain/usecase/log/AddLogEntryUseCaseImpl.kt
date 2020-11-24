package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.AddLogEntryUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddLogEntryUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryMapper,
) : AddLogEntryUseCase {

    override suspend fun invoke(input: LogEntry): Result<Unit> {
        val entity = mapper.map(input, Unit)

        return repository.add(entity)
    }
}