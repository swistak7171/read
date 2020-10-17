package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.di.qualifier.CollectionBookReference
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionBookRepositoryImpl @Inject constructor(
    @CollectionBookReference private val databaseReference: DatabaseReference,
    private val getUser: GetUserUseCase,
) : CollectionBookRepository {

    override suspend fun add(book: CollectionBookEntity): Result<Unit> = withIOContext {
        runCatching {
            databaseReference.push()
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override suspend fun update(book: CollectionBookEntity): Result<Unit> = withIOContext {
        runCatching {
            databaseReference.child(book.id)
                .setValue(book)
                .await()
        }
    }.map { Unit }

    override fun getAll(): Flow<List<CollectionBookEntity>> = entityListFlow {
        val userId = getUser().uid
        databaseReference.orderByChild(CollectionBookEntity.USER_ID_PROPERTY)
            .equalTo(userId)
    }

    override suspend fun getById(id: String): CollectionBookEntity? = withIOContext {
        readEntity { databaseReference.child(id) }
    }
}