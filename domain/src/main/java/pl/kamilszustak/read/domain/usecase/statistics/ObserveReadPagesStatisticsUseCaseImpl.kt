package pl.kamilszustak.read.domain.usecase.statistics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveReadPagesStatisticsUseCase
import pl.kamilszustak.read.model.entity.BookEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveReadPagesStatisticsUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
) : ObserveReadPagesStatisticsUseCase {

    override fun invoke(): Flow<Pair<Int, Int>> {
        return repository.observeAll()
            .map { books ->
                val readPages = books.sumBy(BookEntity::readPages)
                val totalPages = books.sumBy(BookEntity::pagesNumber)

                readPages to totalPages
            }
    }
}