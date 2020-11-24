package pl.kamilszustak.read.domain.access.usecase.goal

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal

interface SetDailyReadingGoalUseCase : CoroutineParametrizedUseCase<ReadingGoal, Result<Unit>>