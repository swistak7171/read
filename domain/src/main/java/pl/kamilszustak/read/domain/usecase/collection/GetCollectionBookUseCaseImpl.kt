package pl.kamilszustak.read.domain.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookEntityMapper
import javax.inject.Inject

class GetCollectionBookUseCaseImpl @Inject constructor(
    private val repository: CollectionBookRepository,
    private val mapper: CollectionBookEntityMapper,
) : GetCollectionBookUseCase {

    override suspend fun invoke(input: CollectionBookId): CollectionBook? =
        repository.getById(input.value)
            .useOrNull { mapper.map(it) }
}