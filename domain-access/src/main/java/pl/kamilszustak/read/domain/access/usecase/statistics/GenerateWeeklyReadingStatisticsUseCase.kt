package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface GenerateWeeklyReadingStatisticsUseCase : CoroutineParametrizedUseCase<SimpleDate, Map<SimpleDate, Int>?>