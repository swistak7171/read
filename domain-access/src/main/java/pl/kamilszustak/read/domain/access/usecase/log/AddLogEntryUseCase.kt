package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface AddLogEntryUseCase : CoroutineParametrizedUseCase<LogEntry, Result<Unit>>