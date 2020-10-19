package pl.kamilszustak.read.domain.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.quote.QuoteEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuoteUseCaseImpl @Inject constructor(
    private val repository: QuoteRepository,
    private val mapper: QuoteEntityMapper,
) : GetQuoteUseCase {

    override suspend fun invoke(input: QuoteId): Quote? =
        repository.getById(input.value)
            .useOrNull { mapper.map(it) }
}