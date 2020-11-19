package pl.kamilszustak.read.domain.access.usecase.goal

import pl.kamilszustak.read.common.date.Time

interface SetReadingGoalUseCase {
    suspend operator fun invoke(pagesNumber: Int, reminderTime: Time): Result<Unit>
}