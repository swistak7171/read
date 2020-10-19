package pl.kamilszustak.read.domain.access.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.Quote

interface GetQuoteUseCase : CoroutineParametrizedUseCase<QuoteId, Quote?>