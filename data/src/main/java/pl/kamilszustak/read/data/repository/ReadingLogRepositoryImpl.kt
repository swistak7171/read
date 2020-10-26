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
    @ReadingLogCollection private val collection: DatabaseCollection,
) : ReadingLogRepository {

    override suspend fun getAll(): List<ReadingLogEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): ReadingLogEntity? {
        TODO("Not yet implemented")
    }

    override fun observeAll(): Flow<List<ReadingLogEntity>> {
        TODO("Not yet implemented")
    }

    override fun observeById(id: String): Flow<ReadingLogEntity> {
        TODO("Not yet implemented")
    }
}