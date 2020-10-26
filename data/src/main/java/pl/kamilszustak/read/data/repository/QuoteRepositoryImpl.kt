package pl.kamilszustak.read.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.data.di.qualifier.QuoteCollection
import pl.kamilszustak.read.model.data.DatabaseCollection
import pl.kamilszustak.read.model.data.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    @QuoteCollection collection: DatabaseCollection,
) : Repository(collection), QuoteRepository {

    override suspend fun add(quote: QuoteEntity): Result<Unit> = addEntity(quote)

    override suspend fun edit(quote: QuoteEntity): Result<Unit> = editEntity(quote)

    override suspend fun deleteById(id: String): Result<Unit> = deleteEntityById(id)

    override suspend fun getAll(): List<QuoteEntity> = getAllEntities()

    override fun observeAll(): Flow<List<QuoteEntity>> = observeAllEntities()

    override suspend fun getById(id: String): QuoteEntity? = getEntityById(id)
}