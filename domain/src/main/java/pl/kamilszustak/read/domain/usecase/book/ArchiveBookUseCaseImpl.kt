package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.book.ArchiveBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArchiveBookUseCaseImpl @Inject constructor(
    private val editBook: EditBookUseCase,
)  : ArchiveBookUseCase {

    override suspend fun invoke(id: BookId): Result<Unit> {
        return runCatching {
            editBook(id) { book ->
                if (book.isArchived) {
                    throw Exception("Book has been already archived")
                }

                book.copy(
                    isArchived = true,
                    archivingDate = Date()
                )
            }
        }
    }
}