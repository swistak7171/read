package pl.kamilszustak.read.model.mapper.goal

import pl.kamilszustak.model.common.id.ReadingGoalId
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class ReadingGoalEntityMapper @Inject constructor() : Mapper<ReadingGoalEntity, ReadingGoal, Unit>() {
    override fun map(model: ReadingGoalEntity, parameter: Unit): ReadingGoal {
        val id = ReadingGoalId(model.id)
        val time = Time.parse(model.reminderTime)

        return ReadingGoal(
            id = id,
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            pagesNumber = model.pagesNumber,
            reminderTime = time
        )
    }
}