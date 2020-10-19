package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.CollectionBookEntity

interface CollectionBookRepository {
    suspend fun add(book: CollectionBookEntity): Result<Unit>
    suspend fun edit(book: CollectionBookEntity): Result<Unit>
    suspend fun deleteById(id: String): Result<Unit>
    fun getAll(): Flow<List<CollectionBookEntity>>
    suspend fun getById(id: String): CollectionBookEntity?
}