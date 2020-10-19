package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.DeleteCollectionBookUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCollectionBookUseCaseImpl @Inject constructor(
    private val repository: CollectionBookRepository,
) : DeleteCollectionBookUseCase {

    override suspend fun invoke(input: CollectionBookId): Result<Unit> =
        repository.deleteById(input.value)
}