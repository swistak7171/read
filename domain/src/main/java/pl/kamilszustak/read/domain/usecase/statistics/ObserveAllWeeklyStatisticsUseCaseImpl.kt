package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveAllWeeklyStatisticsUseCase
import pl.kamilszustak.read.model.entity.LogEntryEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveAllWeeklyStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveAllWeeklyStatisticsUseCase {

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
                            val statistics = outerMapEntry.value.groupBy(LogEntryEntity::creationDate)
                                .mapKeys { SimpleDate.fromDate(it.key) }
                                .mapValues { innerMapEntry ->
                                    innerMapEntry.value.sumBy { logEntry ->
                                        logEntry.endPage - logEntry.startPage
                                    }
                                }

                            weeklyStatistics.add(statistics)
                        }

                        weeklyStatistics
                    }
            }
    }
}