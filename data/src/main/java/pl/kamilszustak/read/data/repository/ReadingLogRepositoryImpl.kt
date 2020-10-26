package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.data.access.repository.ReadingLogRepository
import pl.kamilszustak.read.data.di.qualifier.ReadingLogCollection
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.model.data.ReadingLogEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadingLogRepositoryImpl @Inject constructor(
    @ReadingLogCollection collection: DatabaseCollection,
) : Repository(collection), ReadingLogRepository {

    override suspend fun getAll(): List<ReadingLogEntity> = getAllEntities()

    override suspend fun getById(id: String): ReadingLogEntity? = getEntityById(id)

    override fun observeAll(): Flow<List<ReadingLogEntity>> = observeAllEntities()

    override fun observeById(id: String): Flow<ReadingLogEntity> = observeEntityById(id)
}