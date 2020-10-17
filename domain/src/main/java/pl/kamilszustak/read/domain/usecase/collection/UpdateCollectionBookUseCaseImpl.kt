package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.UpdateCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateCollectionBookUseCaseImpl @Inject constructor(
    private val repository: CollectionBookRepository,
    private val getCollectionBook: GetCollectionBookUseCase,
    private val mapper: CollectionBookMapper,
) : UpdateCollectionBookUseCase {

    override suspend fun invoke(
        id: CollectionBookId,
        update: (CollectionBook) -> CollectionBook
    ): Result<Unit> {
        val book = getCollectionBook(id)

        return runNotNull(book) {
            val updated = update(it)
            val mapped = mapper.map(updated)
            repository.update(mapped)
        }
    }
}