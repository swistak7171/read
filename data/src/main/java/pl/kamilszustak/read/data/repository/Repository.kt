package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.util.entityFlow
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.model.data.Entity

abstract class Repository(
    protected val collection: DatabaseCollection,
) {
    protected suspend inline fun <reified E : Entity> getEntityById(id: String): E? = withIOContext {
        readEntity { collection.reference.child(id) }
    }

    protected suspend inline fun <reified E : Entity> getAllEntities(): List<E> = withIOContext {
        readEntityList(collection.query)
    }

    protected inline fun <reified E : Entity> observeEntityById(id: String): Flow<E> = entityFlow {
        collection.reference.child(id)
    }

    protected inline fun <reified T : Entity> observeAllEntities(): Flow<List<T>> =
        entityListFlow(collection.query)

    protected suspend inline fun <reified T : Entity> addEntity(entity: T): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.push()
                .setValue(entity)
                .await()
        }
    }.map { Unit }

    protected suspend inline fun <reified T : Entity> editEntity(entity: T): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(entity.id)
                .setValue(entity)
                .await()
        }
    }.map { Unit }

    protected suspend fun deleteEntityById(id: String): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(id)
                .removeValue()
                .await()
        }
    }.map { Unit }
}