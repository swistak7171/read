package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.di.qualifier.CollectionBookReference
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionBookRepositoryImpl @Inject constructor(
    @CollectionBookReference private val databaseReference: DatabaseReference,
    private val getUser: GetUserUseCase,
) : CollectionBookRepository {

    override suspend fun add(book: CollectionBookEntity): Result<Unit> {
        return withIOContext {
            databaseReference.push().key.useOrNull { book.id = it }

            runCatching {
                databaseReference.setValue(book).await()
            }
        }.map { Unit }
    }

    override fun getAll(): Flow<List<CollectionBookEntity>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    snapshot.getValue<List<CollectionBookEntity>>()
                        .useOrNull { offer(it) }
                } catch (throwable: Throwable) {
                    Timber.e(throwable)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        val userId = getUser().uid
        val reference = databaseReference.orderByChild(CollectionBookEntity.USER_ID_PROPERTY)
            .equalTo(userId)
            .ref
            .also { it.addValueEventListener(listener) }

        awaitClose {
            reference.removeEventListener(listener)
        }
    }
}