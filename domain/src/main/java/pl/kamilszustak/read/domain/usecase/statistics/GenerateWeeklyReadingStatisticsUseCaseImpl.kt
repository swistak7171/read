package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateWeeklyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveWeeklyReadingStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateWeeklyReadingStatisticsUseCaseImpl @Inject constructor(
    private val observeWeeklyReadingStatistics: ObserveWeeklyReadingStatisticsUseCase,
) : GenerateWeeklyReadingStatisticsUseCase {

    override suspend fun invoke(input: SimpleDate): Map<SimpleDate, Int>? {
        return observeWeeklyReadingStatistics(input)
            .firstOrNull()
    }
}