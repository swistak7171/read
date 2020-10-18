package pl.kamilszustak.read.domain.access.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.Quote

interface EditQuoteUseCase : BaseUseCase {
    suspend operator fun invoke(quoteId: QuoteId, edit: (Quote) -> Quote): Result<Unit>
}