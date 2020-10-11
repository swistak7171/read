package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.CollectionBook

interface AddCollectionBookUseCase : CoroutineParametrizedUseCase<CollectionBook, Result<Unit>>