package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase

interface GenerateAllWeeklyReadingStatisticsUseCase : CoroutineUseCase<List<Map<SimpleDate, Int>>?>