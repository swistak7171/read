package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface GenerateYearlyReadingStatisticsUseCase : CoroutineParametrizedUseCase<Int, Map<Int, Int>?>