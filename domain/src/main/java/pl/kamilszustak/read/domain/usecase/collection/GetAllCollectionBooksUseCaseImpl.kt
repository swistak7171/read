package pl.kamilszustak.read.domain.usecase.collection

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.GetAllCollectionBooksUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCollectionBooksUseCaseImpl @Inject constructor(
    private val collectionBookRepository: CollectionBookRepository,
) : GetAllCollectionBooksUseCase {

    override fun invoke(): Flow<List<CollectionBookEntity>> =
        collectionBookRepository.getAll()
}