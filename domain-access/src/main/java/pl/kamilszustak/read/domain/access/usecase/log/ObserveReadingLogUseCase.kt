package pl.kamilszustak.read.domain.access.usecase.log

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.domain.LogEntry

interface ObserveReadingLogUseCase : UseCase<Flow<List<LogEntry>>>