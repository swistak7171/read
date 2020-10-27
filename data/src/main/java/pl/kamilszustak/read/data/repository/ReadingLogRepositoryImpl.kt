package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.ReadingLogRepository
import pl.kamilszustak.read.data.di.qualifier.ReadingLogCollection
import pl.kamilszustak.read.data.util.entityFlow
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.model.data.LogEntryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadingLogRepositoryImpl @Inject constructor(
    @ReadingLogCollection private val collection: DatabaseCollection,
) : ReadingLogRepository {

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