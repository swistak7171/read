package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.log.DeleteBookReadingLogUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteBookUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
    private val deleteBookReadingLogUseCase: DeleteBookReadingLogUseCase,
) : DeleteBookUseCase {

    override suspend fun invoke(input: BookId): Result<Unit> =
        repository.deleteById(input.value).onSuccess {
            deleteBookReadingLogUseCase(input)
        }
}