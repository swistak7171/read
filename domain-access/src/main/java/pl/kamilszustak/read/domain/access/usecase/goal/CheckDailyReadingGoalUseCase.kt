package pl.kamilszustak.read.domain.access.usecase.goal

import pl.kamilszustak.model.common.ReadingGoalResult
import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase

interface CheckDailyReadingGoalUseCase : CoroutineUseCase<ReadingGoalResult?>