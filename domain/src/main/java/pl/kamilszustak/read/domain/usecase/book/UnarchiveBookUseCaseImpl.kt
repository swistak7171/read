package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.UnarchiveBookUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnarchiveBookUseCaseImpl @Inject constructor(
    private val editBook: EditBookUseCase,
) : UnarchiveBookUseCase {

    override suspend fun invoke(id: BookId): Result<Unit> {
        return runCatching {
            editBook(id) { book ->
                if (!book.isArchived) {
                    throw Exception("Book has not been archived")
                }

                book.copy(
                    isArchived = false,
                    archivingDate = null
                )
            }
        }
    }
}