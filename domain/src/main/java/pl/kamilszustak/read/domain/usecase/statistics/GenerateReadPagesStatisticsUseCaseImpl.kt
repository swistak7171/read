package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.domain.access.usecase.statistics.GenerateReadPagesStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveReadPagesStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateReadPagesStatisticsUseCaseImpl @Inject constructor(
    private val observeReadPagesStatisticsUseCase: ObserveReadPagesStatisticsUseCase,
) : GenerateReadPagesStatisticsUseCase {

    override suspend fun invoke(): Pair<Int, Int>? {
        return observeReadPagesStatisticsUseCase()
            .firstOrNull()
    }
}