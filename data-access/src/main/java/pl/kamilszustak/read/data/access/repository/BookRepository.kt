package pl.kamilszustak.read.data.access.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.model.data.BookEntity

interface BookRepository {
    suspend fun add(book: BookEntity): Result<Unit>
    suspend fun edit(book: BookEntity): Result<Unit>
    suspend fun deleteById(id: String): Result<Unit>
    suspend fun getAll(): List<BookEntity>
    fun observeAll(): Flow<List<BookEntity>>
    fun observeById(id: String): Flow<BookEntity>
    suspend fun getById(id: String): BookEntity?
}