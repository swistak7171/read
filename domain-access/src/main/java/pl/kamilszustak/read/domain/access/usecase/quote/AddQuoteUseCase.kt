package pl.kamilszustak.read.domain.access.usecase.quote

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.Quote

interface AddQuoteUseCase : CoroutineParametrizedUseCase<Quote, Result<Unit>>