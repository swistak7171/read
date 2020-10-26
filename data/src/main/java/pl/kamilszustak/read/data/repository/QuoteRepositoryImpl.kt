package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.data.di.qualifier.QuoteCollection
import pl.kamilszustak.read.data.util.entityListFlow
import pl.kamilszustak.read.data.util.readEntity
import pl.kamilszustak.read.data.util.readEntityList
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    @QuoteCollection private val collection: DatabaseCollection,
    private val getUser: GetUserUseCase,
) : QuoteRepository {

    override suspend fun add(quote: QuoteEntity): Result<Unit> {
        return withIOContext {
            runCatching {
                collection.reference.push()
                    .setValue(quote)
                    .await()
            }
        }.map { Unit }
    }

    override suspend fun edit(quote: QuoteEntity): Result<Unit> = withIOContext {
        runCatching {
            collection.reference.child(quote.id)
                .setValue(quote)
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

    override suspend fun getAll(): Result<List<QuoteEntity>> = withIOContext {
        runCatching { readEntityList(collection.query) }
    }
    override fun observeAll(): Flow<List<QuoteEntity>> = entityListFlow(collection.query)

    override suspend fun getById(id: String): QuoteEntity? = withIOContext {
        readEntity { collection.reference.child(id) }
    }
}