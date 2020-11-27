package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import java.util.*

interface GetReadingLogByDateUseCase : BaseUseCase {
    suspend operator fun invoke(input: Date): List<LogEntry>
    suspend operator fun invoke(year: Int, month: Int, day: Int): List<LogEntry>
}