package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.DateHelper
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.common.date.toSimpleDate
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveWeeklyReadingStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveWeeklyReadingStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveWeeklyReadingStatisticsUseCase {

    override fun invoke(input: SimpleDate): Flow<Map<SimpleDate, Int>> {
        val week = DateHelper.generateWeek(input)
        val weekDays = week.days

        return repository.observeAll()
            .map { logEntries ->
                logEntries.asSequence()
                    .groupBy { it.creationDate.toSimpleDate() }
                    .filterKeys { it in weekDays }
                    .mapValues { mapEntry ->
                        mapEntry.value.sumBy { logEntry ->
                            logEntry.endPage - logEntry.startPage
                        }
                    }
                    .toMutableMap()
                    .also { map ->
                        weekDays.forEach { date ->
                            if (!map.containsKey(date)) {
                                map[date] = 0
                            }
                        }
                    }
                    .toSortedMap()
            }
    }
}