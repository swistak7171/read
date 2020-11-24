package pl.kamilszustak.read.domain.access.usecase.goal

import pl.kamilszustak.read.domain.access.usecase.CoroutineUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal

interface GetLatestDailyReadingGoalUseCase : CoroutineUseCase<ReadingGoal?>