package pl.kamilszustak.read.domain.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.common.util.runNotNull
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.EditQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.quote.QuoteMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditQuoteUseCaseImpl @Inject constructor(
    private val getQuote: GetQuoteUseCase,
    private val repository: QuoteRepository,
    private val mapper: QuoteMapper,
) : EditQuoteUseCase {

    override suspend fun invoke(quoteId: QuoteId, edit: (Quote) -> Quote): Result<Unit> {
        val quote = getQuote(quoteId)

        return runNotNull(quote) {
            val edited = edit(it)
            val mapped = mapper.map(edited)
            repository.edit(mapped)
        }
    }
}