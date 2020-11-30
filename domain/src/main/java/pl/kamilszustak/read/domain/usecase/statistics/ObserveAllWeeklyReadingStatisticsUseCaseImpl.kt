package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveAllWeeklyReadingStatisticsUseCase
import pl.kamilszustak.read.model.entity.LogEntryEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveAllWeeklyReadingStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveAllWeeklyReadingStatisticsUseCase {

    override fun invoke(): Flow<List<Map<SimpleDate, Int>>> {
        val calendar = Calendar.getInstance()

        return repository.observeAll()
            .map { entries ->
                entries.asSequence()
                    .sortedBy(LogEntryEntity::creationDate)
                    .groupBy { entry ->
                        calendar.time = entry.creationDate
                        calendar.get(Calendar.WEEK_OF_YEAR)
                    }
                    .let { map ->
                        val weeklyStatistics = mutableListOf<Map<SimpleDate, Int>>()
                        map.forEach { outerMapEntry ->
                            val statistics = outerMapEntry.value.groupBy { logEntry ->
                                SimpleDate.fromDate(logEntry.creationDate)
                            }
                                .mapValues { innerMapEntry ->
                                    innerMapEntry.value.sumBy { logEntry ->
                                        logEntry.endPage - logEntry.startPage
                                    }
                                }
                                .toMutableMap()
                                .also { map ->
                                    val days = map.toList().toMutableList()
                                    val weekDays = map.map { innerMapEntry ->
                                        innerMapEntry.key.toCalendar()
                                            .get(Calendar.DAY_OF_WEEK)
                                    }
                                        .map { number ->
                                            if (number == 1) {
                                                7
                                            } else {
                                                number - 1
                                            }
                                        }

                                    val firstWeekDay = weekDays.firstOrNull()


                                    for (i in 1..7) {
                                        val weekDay = weekDays.find { it == i }
                                        if (weekDay == null) {
                                            days.add()
                                        }
                                    }
                                }
                                .toSortedMap()

                            weeklyStatistics.add(statistics)
                        }

                        weeklyStatistics
                    }
            }
    }
}