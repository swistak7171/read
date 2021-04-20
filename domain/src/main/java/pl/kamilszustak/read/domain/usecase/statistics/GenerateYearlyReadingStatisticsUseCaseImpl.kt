package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateYearlyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveYearlyReadingStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateYearlyReadingStatisticsUseCaseImpl @Inject constructor(
    private val observeYearlyReadingStatistics: ObserveYearlyReadingStatisticsUseCase
) : GenerateYearlyReadingStatisticsUseCase {

    override suspend fun invoke(input: Int): Map<Int, Int>? =
        observeYearlyReadingStatistics(input)
            .firstOrNull()
}