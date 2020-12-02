package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveReadBooksStatisticsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveReadBooksStatisticsUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
) : ObserveReadBooksStatisticsUseCase {

    override fun invoke(): Flow<Pair<Int, Int>> {
        return repository.observeAll()
            .map { books ->
                val allBooks = books.count()
                val readBooks = books.count { book ->
                    book.readPages == book.pagesNumber
                }

                readBooks to allBooks
            }
    }
}