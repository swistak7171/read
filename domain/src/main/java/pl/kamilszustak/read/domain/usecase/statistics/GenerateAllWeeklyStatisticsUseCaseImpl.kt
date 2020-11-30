package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateAllWeeklyStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveAllWeeklyStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateAllWeeklyStatisticsUseCaseImpl @Inject constructor(
    private val observeAllWeeklyStatistics: ObserveAllWeeklyStatisticsUseCase,
) : GenerateAllWeeklyStatisticsUseCase {

    override suspend fun invoke(): List<Map<SimpleDate, Int>>? {
        return observeAllWeeklyStatistics()
            .firstOrNull()
    }
}