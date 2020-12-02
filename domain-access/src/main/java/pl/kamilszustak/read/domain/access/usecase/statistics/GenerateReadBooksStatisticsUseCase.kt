package pl.kamilszustak.read.domain.access.usecase.statistics

import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase

interface GenerateReadBooksStatisticsUseCase : CoroutineUseCase<Pair<Int, Int>?>