package pl.kamilszustak.read.domain.access.usecase.collection

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface DeleteCollectionBookUseCase : CoroutineParametrizedUseCase<CollectionBookId, Result<Unit>>