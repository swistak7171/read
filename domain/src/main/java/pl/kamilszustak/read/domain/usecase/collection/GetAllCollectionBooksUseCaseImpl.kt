package pl.kamilszustak.read.domain.usecase.collection

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.domain.access.usecase.collection.GetAllCollectionBooksUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.book.CollectionBookEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCollectionBooksUseCaseImpl @Inject constructor(
    private val collectionBookRepository: CollectionBookRepository,
    private val mapper: CollectionBookEntityMapper,
) : GetAllCollectionBooksUseCase {

    override fun invoke(): Flow<List<CollectionBook>> =
        collectionBookRepository.observeAll()
            .map { mapper.mapAll(it) }
}