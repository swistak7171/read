package pl.kamilszustak.read.domain.usecase.log

import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.data.access.repository.LogEntryRepository
import pl.kamilszustak.read.domain.access.usecase.log.DeleteLogEntryUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteLogEntryUseCaseImpl @Inject constructor(
    private val repository: LogEntryRepository,
): DeleteLogEntryUseCase {

    override suspend fun invoke(input: LogEntryId): Result<Unit> =
        repository.deleteById(input.value)
}