package pl.kamilszustak.read.model.mapper.goal

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class ReadingGoalMapper @Inject constructor(
    private val getUser: GetUserUseCase,
) : Mapper<ReadingGoal, ReadingGoalEntity, ReadingGoalType>() {

    private val userId: String by lazy { getUser().id.value }

    override fun map(model: ReadingGoal, parameter: ReadingGoalType): ReadingGoalEntity {
        return ReadingGoalEntity(
            typeName = parameter.name,
            pagesNumber = model.pagesNumber,
            reminderTime = model.reminderTime.format(),
            userId = userId
        )
    }
}