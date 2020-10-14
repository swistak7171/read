package pl.kamilszustak.read.domain.usecase.quote

import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.quote.QuoteMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddQuoteUseCaseImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val quoteMapper: QuoteMapper
) : AddQuoteUseCase {

    override suspend fun invoke(input: Quote): Result<Unit> {
        val entity = quoteMapper.map(input)

        return quoteRepository.add(entity)
    }
}