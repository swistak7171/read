package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.data.di.qualifier.BookCollection
import pl.kamilszustak.read.model.data.BookEntity
import pl.kamilszustak.read.model.data.DatabaseCollection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    @BookCollection collection: DatabaseCollection,
) : Repository(collection), BookRepository {

    override suspend fun add(book: BookEntity): Result<Unit> = addEntity(book)

    override suspend fun edit(book: BookEntity): Result<Unit> = editEntity(book)

    override suspend fun deleteById(id: String): Result<Unit> = deleteEntityById(id)

    override suspend fun getAll(): List<BookEntity> = getAllEntities()

    override fun observeAll(): Flow<List<BookEntity>> = observeAllEntities()

    override suspend fun getById(id: String): BookEntity? = getEntityById(id)
}