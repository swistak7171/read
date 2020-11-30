package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase

interface GenerateAllWeeklyStatisticsUseCase : CoroutineUseCase<List<Map<SimpleDate, Int>>?>