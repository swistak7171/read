package pl.kamilszustak.read.domain.usecase.quote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.domain.access.usecase.quote.GetAllQuotesUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.quote.QuoteEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllQuotesUseCaseImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val mapper: QuoteEntityMapper,
) : GetAllQuotesUseCase {

    override fun invoke(): Flow<List<Quote>> =
        quoteRepository.getAll()
            .map { mapper.mapAll(it) }
}