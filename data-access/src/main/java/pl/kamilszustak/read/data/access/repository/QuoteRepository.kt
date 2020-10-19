package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.QuoteEntity

interface QuoteRepository {
    suspend fun add(quote: QuoteEntity): Result<Unit>
    suspend fun edit(quote: QuoteEntity): Result<Unit>
    fun getAll(): Flow<List<QuoteEntity>>
    suspend fun getById(id: String): QuoteEntity?
}