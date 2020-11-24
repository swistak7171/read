package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.ReadingGoalRepository
import pl.kamilszustak.read.data.di.qualifier.ReadingGoalCollection
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.entity.DatabaseCollection
import pl.kamilszustak.read.model.entity.goal.ReadingGoalEntity
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadingGoalRepositoryImpl @Inject constructor(
    @ReadingGoalCollection private val collection: DatabaseCollection,
    private val getUser: GetUserUseCase,
) : ReadingGoalRepository {

    override suspend fun get(type: ReadingGoalType): ReadingGoalEntity? =
        readEntity {
            collection.query.orderByChild(ReadingGoalEntity.TYPE_NAME_PROPERTY)
                .equalTo(type.name)
        }

    override suspend fun set(goal: ReadingGoalEntity): Result<Unit> = withIOContext {
        val userId = getUser().id.value

        runCatching {
            val goals = readEntityList<ReadingGoalEntity> {
                collection.query.orderByChild(ReadingGoalEntity.USER_ID_PROPERTY)
                    .equalTo(userId)
                    .orderByChild(ReadingGoalEntity.TYPE_NAME_PROPERTY)
                    .equalTo(goal.typeName)
            }

            goals.forEach { goal ->
                collection.reference.child(goal.id)
                    .removeValue()
            }

            collection.reference.push()
                .setValue(goal)
                .await()
        }
    }.map { Unit }
}