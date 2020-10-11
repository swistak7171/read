package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity

interface AddCollectionBookUseCase : CoroutineParametrizedUseCase<CollectionBookEntity, Result<Unit>>