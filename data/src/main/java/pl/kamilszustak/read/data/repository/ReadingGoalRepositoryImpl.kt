package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.ReadingGoalRepository
import pl.kamilszustak.read.data.di.qualifier.ReadingGoalCollection
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.model.entity.DatabaseCollection
import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadingGoalRepositoryImpl @Inject constructor(
    @ReadingGoalCollection private val collection: DatabaseCollection,
) : ReadingGoalRepository {

    override suspend fun getAll(type: ReadingGoalType): List<ReadingGoalEntity> =
        readEntityList(collection.query)

    override suspend fun getLatest(type: ReadingGoalType): ReadingGoalEntity? {
        return getAll(type)
            .asSequence()
            .filter { it.typeName == type.name }
            .maxByOrNull(ReadingGoalEntity::creationDate)
    }

    override suspend fun add(goal: ReadingGoalEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.push()
                .setValue(goal)
                .await()
        }
    }.map { Unit }
}