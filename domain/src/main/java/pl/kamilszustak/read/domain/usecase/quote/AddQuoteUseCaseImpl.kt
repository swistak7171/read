package pl.kamilszustak.read.domain.usecase.quote

import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddQuoteUseCaseImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
) : AddQuoteUseCase {

    override suspend fun invoke(input: Quote): Result<Unit> =
        quoteRepository.add(input)
}