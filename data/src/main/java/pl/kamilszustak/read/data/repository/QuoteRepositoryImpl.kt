package pl.kamilszustak.read.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.core.view.QuerySpec
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.data.di.qualifier.QuoteReference
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.valueEventFlow
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.QuoteEntity
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    @QuoteReference private val databaseReference: DatabaseReference,
    private val getUser: GetUserUseCase
) : QuoteRepository {

    override suspend fun add(quote: QuoteEntity): Result<Unit> {
        return withIOContext {
            runCatching {
                databaseReference.push()
                    .setValue(quote)
                    .await()
            }
        }.map { Unit }
    }

    override fun getAll(): Flow<List<QuoteEntity>> = entityListFlow {
        val userId = getUser().uid
        databaseReference.orderByChild(QuoteEntity.USER_ID_PROPERTY)
            .equalTo(userId)
    }
}