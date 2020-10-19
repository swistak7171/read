package pl.kamilszustak.read.domain.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.DeleteQuoteUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteQuoteUseCaseImpl @Inject constructor(
    private val repository: QuoteRepository,
) : DeleteQuoteUseCase {

    override suspend fun invoke(input: QuoteId): Result<Unit> =
        repository.deleteById(input.value)
}