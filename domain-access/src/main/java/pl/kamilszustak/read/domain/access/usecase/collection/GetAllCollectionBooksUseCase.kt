package pl.kamilszustak.read.domain.access.usecase.collection

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity

interface GetAllCollectionBooksUseCase : UseCase<Flow<List<CollectionBookEntity>>>