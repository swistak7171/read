package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.AddBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.mapper.book.BookMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddBookUseCaseImpl @Inject constructor(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper
) : AddBookUseCase {

    override suspend fun invoke(input: Book): Result<Unit> {
        val entity = bookMapper.map(input)

        return bookRepository.add(entity)
    }
}