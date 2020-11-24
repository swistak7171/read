package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.data.di.qualifier.ReadingLogCollection
import pl.kamilszustak.read.data.util.entityFlow
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.model.entity.DatabaseCollection
import pl.kamilszustak.read.model.entity.LogEntryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntryRepositoryImpl @Inject constructor(
    @ReadingLogCollection private val collection: DatabaseCollection,
) : LogEntryRepository {

    override suspend fun add(entry: LogEntryEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.push()
                .setValue(entry)
                .await()
        }
    }.map { Unit }

    override suspend fun edit(entry: LogEntryEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(entry.id)
                .setValue(entry)
                .await()
        }
    }.map { Unit }

    override suspend fun deleteById(id: String): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(id)
                .removeValue()
                .await()
        }
    }.map { Unit }

    override suspend fun deleteAllByBookId(id: String): Result<Unit> = withIOContext {
        runCatching {
            val entries = readEntityList<LogEntryEntity> {
                collection.query.orderByChild(LogEntryEntity.BOOK_ID_PROPERTY)
                    .equalTo(id)
            }

            entries.forEach { entry ->
                collection.reference.child(entry.id)
                    .removeValue()
            }
        }
    }

    override suspend fun getAll(): List<LogEntryEntity> = withIOContext {
        readEntityList(collection.query)
    }

    override suspend fun getById(id: String): LogEntryEntity? = withIOContext {
        readEntity { collection.reference.child(id) }
    }

    override fun observeAll(): Flow<List<LogEntryEntity>> =
        entityListFlow(collection.query)

    override fun observeById(id: String): Flow<LogEntryEntity> =
        entityFlow { collection.reference.child(id) }
}