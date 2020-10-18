package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCollectionBookUseCaseImpl @Inject constructor(
    private val collectionBookRepository: CollectionBookRepository,
    private val collectionBookMapper: CollectionBookMapper
) : AddCollectionBookUseCase {

    override suspend fun invoke(input: CollectionBook): Result<Unit> {
        val entity = collectionBookMapper.map(input)

        return collectionBookRepository.add(entity)
    }
}