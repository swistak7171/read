package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.qualifier.CollectionBookReference
import pl.kamilszustak.read.model.data.CollectionBookEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionBookRepositoryImpl @Inject constructor(
    @CollectionBookReference private val databaseReference: DatabaseReference,
) : CollectionBookRepository {

    override suspend fun add(book: CollectionBookEntity): Result<Unit> {
        return withIOContext {
            databaseReference.push().key.useOrNull { book.id = it }

            runCatching {
                databaseReference.setValue(book).await()
            }
        }.map { Unit }
    }
}