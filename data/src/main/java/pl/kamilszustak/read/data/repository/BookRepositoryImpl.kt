package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.data.di.qualifier.BookCollection
import pl.kamilszustak.read.data.util.entityFlow
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.model.entity.BookEntity
import pl.kamilszustak.read.model.entity.DatabaseCollection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    @BookCollection collection: DatabaseCollection,
) : Repository(collection), BookRepository {

    override suspend fun add(book: BookEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.push()
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override suspend fun edit(book: BookEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(book.id)
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override suspend fun deleteById(id: String): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(id)
                .removeValue()
                .await()
        }
    }.map { Unit }

    override suspend fun getAll(): List<BookEntity> = withIOContext {
        readEntityList(collection.query)
    }

    override suspend fun getById(id: String): BookEntity? = withIOContext {
        readEntity { collection.reference.child(id) }
    }

    override fun observeAll(): Flow<List<BookEntity>> =
        entityListFlow(collection.query)

    override fun observeById(id: String): Flow<BookEntity> =
        entityFlow { collection.reference.child(id) }
}