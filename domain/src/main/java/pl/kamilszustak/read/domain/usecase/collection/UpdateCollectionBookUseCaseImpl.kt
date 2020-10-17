package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.UpdateCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateCollectionBookUseCaseImpl @Inject constructor(
    private val collectionBookRepository: CollectionBookRepository,
    private val mapper: CollectionBookMapper,
) : UpdateCollectionBookUseCase {

    override suspend fun invoke(bookId: String, update: (CollectionBook) -> CollectionBook) {
        TODO("Not yet implemented")
    }
}