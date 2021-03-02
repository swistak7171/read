package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.mapper.book.BookEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBookUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
    private val mapper: BookEntityMapper,
): GetBookUseCase {

    override suspend fun invoke(input: BookId): Book? =
        repository.getById(input.value)
            ?.let { mapper.map(it, Unit) }
}