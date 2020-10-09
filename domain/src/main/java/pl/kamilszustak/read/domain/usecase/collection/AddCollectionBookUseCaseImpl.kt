package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCollectionBookUseCaseImpl @Inject constructor(
    private val collectionBookRepository: CollectionBookRepository,
) : AddCollectionBookUseCase {

    override suspend fun invoke(input: CollectionBook): Result<Unit> =
        collectionBookRepository.add(input)
}