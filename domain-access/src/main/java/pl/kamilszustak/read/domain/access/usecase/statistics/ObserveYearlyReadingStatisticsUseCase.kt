package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase

interface ObserveYearlyReadingStatisticsUseCase : ParametrizedUseCase<Int, Flow<Map<Int, Int>>>