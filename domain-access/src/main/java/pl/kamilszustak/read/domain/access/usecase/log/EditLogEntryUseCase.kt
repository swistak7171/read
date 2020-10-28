package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface EditLogEntryUseCase : BaseUseCase {
    suspend operator fun invoke(id: LogEntryId, edit: (LogEntry) -> LogEntry): Result<Unit>
}