package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase

interface ObserveReadBooksStatisticsUseCase : UseCase<Flow<Pair<Int, Int>>>