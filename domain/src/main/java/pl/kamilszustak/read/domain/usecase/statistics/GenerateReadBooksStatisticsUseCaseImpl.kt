package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateReadBooksStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveReadBooksStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateReadBooksStatisticsUseCaseImpl @Inject constructor(
    private val observeReadBooksStatistics: ObserveReadBooksStatisticsUseCase
) : GenerateReadBooksStatisticsUseCase {

    override suspend fun invoke(): Pair<Int, Int>? {
        return observeReadBooksStatistics()
            .firstOrNull()
    }
}