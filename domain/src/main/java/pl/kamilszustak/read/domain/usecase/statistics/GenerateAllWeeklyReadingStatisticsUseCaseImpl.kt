package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateAllWeeklyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveAllWeeklyReadingStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateAllWeeklyReadingStatisticsUseCaseImpl @Inject constructor(
    private val observeAllWeeklyReadingStatistics: ObserveAllWeeklyReadingStatisticsUseCase,
) : GenerateAllWeeklyReadingStatisticsUseCase {

    override suspend fun invoke(): List<Map<SimpleDate, Int>>? {
        return observeAllWeeklyReadingStatistics()
            .firstOrNull()
    }
}