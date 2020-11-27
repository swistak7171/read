package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveMonthlyReadingStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveMonthlyReadingStatisticsUseCase {

    override fun invoke(year: Int, month: Int): Flow<Map<String, Int>> {
        val calendar = Calendar.getInstance()
        val monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        return repository.observeAll()
            .map { entries ->
                entries.asSequence()
                    .filter { entry ->
                        calendar.time = entry.creationDate
                        val entryYear = calendar.get(Calendar.YEAR)
                        val entryMonth = calendar.get(Calendar.MONTH) + 1

                        (year == entryYear && month == entryMonth)
                    }
                    .sortedBy { it.creationDate }
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
                    .mapKeys { it.key.toString() }
            }
    }
}