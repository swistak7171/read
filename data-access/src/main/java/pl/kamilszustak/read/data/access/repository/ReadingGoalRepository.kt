package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType

interface ReadingGoalRepository {
    suspend fun getAll(type: ReadingGoalType): List<ReadingGoalEntity>
    suspend fun getLatest(type: ReadingGoalType): ReadingGoalEntity?
    suspend fun add(goal: ReadingGoalEntity): Result<Unit>
}