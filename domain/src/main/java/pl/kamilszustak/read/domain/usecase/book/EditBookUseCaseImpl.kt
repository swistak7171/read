package pl.kamilszustak.read.domain.usecase.book

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.domain.access.usecase.log.AddLogEntryUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.book.BookMapper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditBookUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
    private val getBook: GetBookUseCase,
    private val mapper: BookMapper,
    private val addLogEntry: AddLogEntryUseCase,
) : EditBookUseCase {

    override suspend fun invoke(
        id: BookId,
        edit: (Book) -> Book
    ): Result<Unit> {
        val book = getBook(id)

        return runNotNull(book) { originalBook ->
            val editedBook = edit(originalBook)
            val mapped = mapper.map(editedBook).apply {
                modificationDate = Date()
            }

            repository.edit(mapped).also {
                if (editedBook.readPages > originalBook.readPages) {
                    val entry = LogEntry(
                        book = editedBook,
                        readPages = (editedBook.readPages - originalBook.readPages)
                    )

                    coroutineScope {
                        launch { addLogEntry(entry) }
                    }
                }
            }
        }
    }
}