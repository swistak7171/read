package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.UseCase

interface ObserveAllWeeklyReadingStatisticsUseCase : UseCase<Flow<List<Map<SimpleDate, Int>>>>