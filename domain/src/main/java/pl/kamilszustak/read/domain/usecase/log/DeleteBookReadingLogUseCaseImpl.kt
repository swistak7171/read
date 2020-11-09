package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.DeleteBookReadingLogUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteBookReadingLogUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
) : DeleteBookReadingLogUseCase {

    override suspend fun invoke(input: BookId): Result<Unit> =
        repository.deleteAllByBookId(input.value)
}