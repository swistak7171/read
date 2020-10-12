package pl.kamilszustak.read.domain.access.usecase.quote

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.domain.Quote

interface GetAllQuotesUseCase : UseCase<Flow<List<Quote>>>