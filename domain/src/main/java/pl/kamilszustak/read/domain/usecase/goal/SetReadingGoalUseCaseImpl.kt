package pl.kamilszustak.read.domain.usecase.goal

import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.domain.access.usecase.goal.SetReadingGoalUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetReadingGoalUseCaseImpl @Inject constructor(
) : SetReadingGoalUseCase {

    override suspend fun invoke(pagesNumber: Int, reminderTime: Time): Result<Unit> {
        return Result.success(Unit)
    }
}