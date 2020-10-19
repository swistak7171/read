package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.EditCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditCollectionBookUseCaseImpl @Inject constructor(
    private val repository: CollectionBookRepository,
    private val getCollectionBook: GetCollectionBookUseCase,
    private val mapper: CollectionBookMapper,
) : EditCollectionBookUseCase {

    override suspend fun invoke(
        id: CollectionBookId,
        edit: (CollectionBook) -> CollectionBook
    ): Result<Unit> {
        val book = getCollectionBook(id)

        return runNotNull(book) {
            val edited = edit(it)
            val mapped = mapper.map(edited)
            repository.edit(mapped)
        }
    }
}