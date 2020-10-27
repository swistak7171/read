package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.EditLogEntryUseCase
import pl.kamilszustak.read.domain.access.usecase.log.GetLogEntryUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryMapper
import java.util.*
import javax.inject.Inject

class EditLogEntryUseCaseImpl @Inject constructor(
    private val getLogEntry: GetLogEntryUseCase,
    private val repository: LogEntryRepository,
    private val mapper: LogEntryMapper,
) : EditLogEntryUseCase {

    override suspend fun invoke(id: LogEntryId, edit: (LogEntry) -> LogEntry): Result<Unit> {
        val entry = getLogEntry(id)

        return runNotNull(entry) {
            val edited = edit(it)
            val mapped = mapper.map(edited).apply {
                modificationDate = Date()
            }

            repository.edit(mapped)
        }
    }
}