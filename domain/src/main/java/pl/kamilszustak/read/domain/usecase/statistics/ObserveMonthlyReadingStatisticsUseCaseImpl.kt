package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import pl.kamilszustak.read.model.entity.LogEntryEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveMonthlyReadingStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveMonthlyReadingStatisticsUseCase {

    override fun invoke(date: SimpleDate): Flow<Map<SimpleDate, Int>> {
        val calendar = Calendar.getInstance()
        val monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        return repository.observeAll()
            .map { entries ->
                entries.asSequence()
                    .filter { entry ->
                        calendar.time = entry.creationDate
                        val entryDate = SimpleDate.fromCalendar(calendar)

                        (date.year == entryDate.year && date.month == entryDate.month)
                    }
                    .sortedBy(LogEntryEntity::creationDate)
                    .groupBy { entry ->
                        calendar.time = entry.creationDate
                        calendar.get(Calendar.DAY_OF_MONTH)
                    }
                    .mapValues { mapEntry ->
                        mapEntry.value.sumBy { entry ->
                            entry.endPage - entry.startPage
                        }
                    }
                    .toMutableMap()
                    .also { map ->
                        for (i in 1..monthLength) {
                            if (!map.containsKey(i)) {
                                map[i] = 0
                            }
                        }
                    }
                    .toSortedMap()
                    .mapKeys { mapEntry ->
                        date.copy(
                            day = mapEntry.key
                        )
                    }
            }
    }
}