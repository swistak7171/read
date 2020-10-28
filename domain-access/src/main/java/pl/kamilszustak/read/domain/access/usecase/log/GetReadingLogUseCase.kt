package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface GetReadingLogUseCase : CoroutineUseCase<List<LogEntry>>