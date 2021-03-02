package pl.kamilszustak.read.domain.usecase.goal

import pl.kamilszustak.read.data.access.repository.ReadingGoalRepository
import pl.kamilszustak.read.domain.access.usecase.goal.GetLatestDailyReadingGoalUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import pl.kamilszustak.read.model.mapper.goal.ReadingGoalEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLatestDailyReadingGoalUseCaseImpl @Inject constructor(
    private val repository: ReadingGoalRepository,
    private val mapper: ReadingGoalEntityMapper,
) : GetLatestDailyReadingGoalUseCase {

    override suspend fun invoke(): ReadingGoal? {
        val goal = repository.getLatest(ReadingGoalType.DAILY)

        return goal?.let { mapper.map(it, Unit) }
    }
}