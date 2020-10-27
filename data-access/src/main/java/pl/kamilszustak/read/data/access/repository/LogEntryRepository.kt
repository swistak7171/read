package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.LogEntryEntity

interface LogEntryRepository {
    suspend fun getAll(): List<LogEntryEntity>
    suspend fun getById(id: String): LogEntryEntity?
    fun observeAll(): Flow<List<LogEntryEntity>>
    fun observeById(id: String): Flow<LogEntryEntity>
}