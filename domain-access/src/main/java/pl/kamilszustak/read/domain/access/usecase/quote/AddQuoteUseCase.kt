package pl.kamilszustak.read.domain.access.usecase.quote

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.data.QuoteEntity

interface AddQuoteUseCase : CoroutineParametrizedUseCase<QuoteEntity, Result<Unit>>