package pl.kamilszustak.read.domain.access.usecase.statistics

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase

interface ObserveReadPagesStatisticsUseCase : UseCase<Flow<Pair<Int, Int>>>