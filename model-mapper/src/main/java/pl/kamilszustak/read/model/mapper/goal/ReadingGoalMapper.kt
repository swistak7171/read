package pl.kamilszustak.read.model.mapper.goal

import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class ReadingGoalMapper @Inject constructor() : Mapper<ReadingGoal, ReadingGoalEntity, ReadingGoalType>() {
    override fun map(model: ReadingGoal, parameter: ReadingGoalType): ReadingGoalEntity {
        
    }
}