package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.data.qualifier.QuoteReference
import pl.kamilszustak.read.model.data.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    @QuoteReference private val databaseReference: DatabaseReference,
) : QuoteRepository {

    override suspend fun add(quote: QuoteEntity): Result<Unit> {
        return withIOContext {
            databaseReference.push().key.useOrNull { quote.id = it }

            runCatching {
                databaseReference.setValue(quote).await()
            }
        }.map { Unit }
    }
}