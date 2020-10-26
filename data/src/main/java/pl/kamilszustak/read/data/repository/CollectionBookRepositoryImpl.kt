package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.di.qualifier.CollectionBookReference
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionBookRepositoryImpl @Inject constructor(
    @CollectionBookReference private val databaseReference: DatabaseReference,
    private val getUser: GetUserUseCase,
) : CollectionBookRepository {

    private val booksQuery: Query
        get() {
            val userId = getUser().uid

            return databaseReference.orderByChild(CollectionBookEntity.USER_ID_PROPERTY)
                .equalTo(userId)
        }

    override suspend fun add(book: CollectionBookEntity): Result<Unit> = withIOContext {
        runCatching {
            databaseReference.push()
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override suspend fun edit(book: CollectionBookEntity): Result<Unit> = withIOContext {
        runCatching {
            databaseReference.child(book.id)
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override suspend fun deleteById(id: String): Result<Unit> = withIOContext {
        runCatching {
            databaseReference.child(id)
                .removeValue()
                .await()
        }
    }.map { Unit }

    override suspend fun getAll(): Result<List<CollectionBookEntity>> = withIOContext {
        runCatching { readEntityList(booksQuery) }
    }

    override fun observeAll(): Flow<List<CollectionBookEntity>> = entityListFlow(booksQuery)

    override suspend fun getById(id: String): CollectionBookEntity? = withIOContext {
        readEntity { databaseReference.child(id) }
    }
}