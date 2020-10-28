package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface GetLogEntryUseCase : CoroutineParametrizedUseCase<LogEntryId, LogEntry?>