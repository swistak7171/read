package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.entity.LogEntryEntity
import java.util.*

interface LogEntryRepository {
    suspend fun add(entry: LogEntryEntity): Result<Unit>
    suspend fun edit(entry: LogEntryEntity): Result<Unit>
    suspend fun deleteById(id: String): Result<Unit>
    suspend fun deleteAllByBookId(id: String): Result<Unit>
    suspend fun getAll(): List<LogEntryEntity>
    suspend fun getAllByDate(date: Date): List<LogEntryEntity>
    suspend fun getById(id: String): LogEntryEntity?
    fun observeAll(): Flow<List<LogEntryEntity>>
    fun observeById(id: String): Flow<LogEntryEntity>
}