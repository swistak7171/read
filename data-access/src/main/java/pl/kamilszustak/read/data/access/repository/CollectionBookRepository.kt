package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.CollectionBookEntity

interface CollectionBookRepository {
    suspend fun add(book: CollectionBookEntity): Result<Unit>
    fun getAll(): Flow<List<CollectionBookEntity>>
}