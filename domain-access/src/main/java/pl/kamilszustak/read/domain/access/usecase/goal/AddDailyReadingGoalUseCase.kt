package pl.kamilszustak.read.domain.access.usecase.goal

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal

interface AddDailyReadingGoalUseCase : CoroutineParametrizedUseCase<ReadingGoal, Result<Unit>>