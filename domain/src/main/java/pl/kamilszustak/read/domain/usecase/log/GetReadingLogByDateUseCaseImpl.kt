package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.read.common.date.DateFormats
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.GetReadingLogByDateUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.log.LogEntryEntityMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetReadingLogByDateUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
    private val mapper: LogEntryEntityMapper,
) : GetReadingLogByDateUseCase {

    override suspend fun invoke(input: Date): List<LogEntry> {
        val dateFormat = DateFormats.dateFormat
        val formattedDate = dateFormat.format(input)
        val entries = repository.getAll()
            .filter { entry ->
                dateFormat.format(entry.creationDate) == formattedDate
            }

        return mapper.mapAll(entries, Unit)
    }

    override suspend fun invoke(year: Int, month: Int, day: Int): List<LogEntry> {
        val calendar = Calendar.getInstance()
        val entries = repository.getAll()
            .filter { entry ->
                calendar.time = entry.creationDate
                val entryYear = calendar.get(Calendar.YEAR)
                val entryMonth = calendar.get(Calendar.MONTH)
                val entryDay = calendar.get(Calendar.DAY_OF_MONTH)

                (year == entryYear && month == entryMonth && day == entryDay)
            }

        return mapper.mapAll(entries, Unit)
    }
}