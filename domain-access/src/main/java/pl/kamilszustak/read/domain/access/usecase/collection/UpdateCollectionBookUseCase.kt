package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.model.domain.CollectionBook

interface UpdateCollectionBookUseCase {
    suspend operator fun invoke(id: CollectionBookId, update: (CollectionBook) -> CollectionBook): Result<Unit>
}