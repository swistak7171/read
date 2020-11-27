package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase

interface ObserveMonthlyReadingStatisticsUseCase : BaseUseCase {
    operator fun invoke(year: Int, month: Int): Flow<Map<String, Int>>
}