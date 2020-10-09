package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase
import pl.kamilszustak.read.model.domain.CollectionBook

interface AddCollectionBookUseCase : ParametrizedUseCase<CollectionBook, Unit>