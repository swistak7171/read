package pl.kamilszustak.read.domain.usecase.book

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.ObserveAllBooksUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.mapper.book.BookEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveAllBooksUseCaseImpl @Inject constructor(
    private val bookRepository: BookRepository,
    private val mapper: BookEntityMapper,
) : ObserveAllBooksUseCase {

    override fun invoke(): Flow<List<Book>> =
        bookRepository.observeAll()
            .map { mapper.mapAll(it) }
}