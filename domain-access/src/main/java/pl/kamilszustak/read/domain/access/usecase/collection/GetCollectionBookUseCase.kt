package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.CollectionBook

interface GetCollectionBookUseCase : CoroutineParametrizedUseCase<CollectionBookId, CollectionBook?>