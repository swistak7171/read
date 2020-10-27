package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface DeleteLogEntryUseCase : CoroutineParametrizedUseCase<LogEntryId, Result<Unit>>