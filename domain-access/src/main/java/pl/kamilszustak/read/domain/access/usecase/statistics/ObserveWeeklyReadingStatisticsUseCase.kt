package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase

interface ObserveWeeklyReadingStatisticsUseCase : ParametrizedUseCase<SimpleDate, Flow<Map<SimpleDate, Int>>>