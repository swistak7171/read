package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import java.util.*

interface GetReadingLogByDateUseCase : CoroutineParametrizedUseCase<Date, List<LogEntry>>