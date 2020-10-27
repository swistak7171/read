package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.mapper.book.BookMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditBookUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
    private val getBook: GetBookUseCase,
    private val mapper: BookMapper,
) : EditBookUseCase {

    override suspend fun invoke(
        id: BookId,
        edit: (Book) -> Book
    ): Result<Unit> {
        val book = getBook(id)

        return runNotNull(book) {
            val edited = edit(it)
            val mapped = mapper.map(edited).apply {
                modificationDate = Date()
            }

            repository.edit(mapped)
        }
    }
}