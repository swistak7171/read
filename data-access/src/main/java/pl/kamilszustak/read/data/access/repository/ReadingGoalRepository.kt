package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType

interface ReadingGoalRepository {
    suspend fun get(type: ReadingGoalType): ReadingGoalEntity?
    suspend fun set(goal: ReadingGoalEntity): Result<Unit>
}