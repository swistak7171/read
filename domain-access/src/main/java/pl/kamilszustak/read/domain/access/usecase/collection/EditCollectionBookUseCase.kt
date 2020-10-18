package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.CollectionBook

interface EditCollectionBookUseCase : BaseUseCase {
    suspend operator fun invoke(id: CollectionBookId, edit: (CollectionBook) -> CollectionBook): Result<Unit>
}