package pl.kamilszustak.read.domain.usecase.quote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.ObserveAllQuotesUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.quote.QuoteEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveAllQuotesUseCaseImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val mapper: QuoteEntityMapper
) : ObserveAllQuotesUseCase {

    override fun invoke(): Flow<List<Quote>> =
        quoteRepository.observeAll()
            .map { mapper.mapAll(it) }
}