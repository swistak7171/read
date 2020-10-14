package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.data.di.qualifier.QuoteReference
import pl.kamilszustak.read.data.util.valueEventFlow
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    @QuoteReference private val databaseReference: DatabaseReference,
    private val getUser: GetUserUseCase
) : QuoteRepository {

    override suspend fun add(quote: QuoteEntity): Result<Unit> {
        return withIOContext {
            databaseReference.push().key.useOrNull { quote.id = it }

            runCatching {
                databaseReference.setValue(quote).await()
            }
        }.map { Unit }
    }

    override fun getAll(): Flow<List<QuoteEntity>> = valueEventFlow {
        val userId = getUser().uid
        databaseReference.orderByChild(QuoteEntity.USER_ID_PROPERTY)
            .equalTo(userId)
            .ref
    }
}