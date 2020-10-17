package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.CollectionBook

interface UpdateCollectionBookUseCase : BaseUseCase {
    suspend operator fun invoke(bookId: String, update: (CollectionBook) -> CollectionBook)
}