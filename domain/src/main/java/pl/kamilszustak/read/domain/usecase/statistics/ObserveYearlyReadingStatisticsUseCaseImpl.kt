package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveYearlyReadingStatisticsUseCase
import pl.kamilszustak.read.model.entity.LogEntryEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveYearlyReadingStatisticsUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : ObserveYearlyReadingStatisticsUseCase {

    override fun invoke(input: Int): Flow<Map<Int, Int>> {
        val calendar = Calendar.getInstance()

        return repository.observeAll()
            .map { entries ->
                entries.asSequence()
                    .filter { entry ->
                        val date = SimpleDate.fromDate(entry.creationDate)
                        (date.year == input)
                    }
                    .sortedBy(LogEntryEntity::creationDate)
                    .groupBy { entry ->
                        calendar.time = entry.creationDate
                        calendar.get(Calendar.MONTH)
                    }
                    .mapValues { mapEntry ->
                        mapEntry.value.sumBy { entry ->
                            entry.endPage - entry.startPage
                        }
                    }
                    .toMutableMap()
                    .also { map ->
                        for (i in 1..12) {
                            if (!map.containsKey(i)) {
                                map[i] = 0
                            }
                        }
                    }
                    .toSortedMap()
            }
    }
}