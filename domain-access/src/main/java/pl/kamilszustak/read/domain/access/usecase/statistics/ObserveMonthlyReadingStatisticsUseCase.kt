package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase

interface ObserveMonthlyReadingStatisticsUseCase : BaseUseCase {
    operator fun invoke(date: SimpleDate): Flow<Map<SimpleDate, Int>>
}