package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.GetLogEntryUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLogEntryUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : GetLogEntryUseCase {

    override suspend fun invoke(input: LogEntryId): LogEntry? =
        repository.getById(input.value)
            ?.let { mapper.map(it, Unit) }
}