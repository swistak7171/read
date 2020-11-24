package pl.kamilszustak.read.domain.usecase.goal

import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.ReadingGoalRepository
import pl.kamilszustak.read.domain.access.usecase.goal.GetDailyReadingGoalUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import pl.kamilszustak.read.model.mapper.goal.ReadingGoalEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDailyReadingGoalUseCaseImpl @Inject constructor(
    private val repository: ReadingGoalRepository,
    private val mapper: ReadingGoalEntityMapper,
) : GetDailyReadingGoalUseCase {

    override suspend fun invoke(input: Unit): ReadingGoal? {
        val goal = repository.get(ReadingGoalType.DAILY)

        return goal.useOrNull { mapper.map(it, Unit) }
    }
}