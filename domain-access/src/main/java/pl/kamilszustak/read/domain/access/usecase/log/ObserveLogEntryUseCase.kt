package pl.kamilszustak.read.domain.access.usecase.log

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface ObserveLogEntryUseCase : ParametrizedUseCase<LogEntryId, Flow<LogEntry>>