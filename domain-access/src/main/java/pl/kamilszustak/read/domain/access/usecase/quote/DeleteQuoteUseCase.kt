package pl.kamilszustak.read.domain.access.usecase.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface DeleteQuoteUseCase : CoroutineParametrizedUseCase<QuoteId, Result<Unit>>