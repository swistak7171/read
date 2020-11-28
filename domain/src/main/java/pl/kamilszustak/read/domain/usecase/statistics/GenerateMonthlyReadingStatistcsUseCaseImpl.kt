package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateMonthlyReadingStatistcsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateMonthlyReadingStatistcsUseCaseImpl @Inject constructor(
    private val observeMonthlyReadingStatistics: ObserveMonthlyReadingStatisticsUseCase,
) : GenerateMonthlyReadingStatistcsUseCase {

    override suspend fun invoke(date: SimpleDate): Map<String, Int>? {
        return observeMonthlyReadingStatistics(date)
            .firstOrNull()
    }
}