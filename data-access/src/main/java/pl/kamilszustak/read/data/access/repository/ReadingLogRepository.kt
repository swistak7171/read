package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.ReadingLogEntity

interface ReadingLogRepository {
    suspend fun getAll(): List<ReadingLogEntity>
    suspend fun getById(id: String): ReadingLogEntity?
    fun observeAll(): Flow<List<ReadingLogEntity>>
    fun observeById(id: String): Flow<ReadingLogEntity>
}