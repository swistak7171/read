package pl.kamilszustak.read.domain.access.usecase.book

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.ParametrizedUseCase
import pl.kamilszustak.read.model.domain.Book

interface ObserveBookUseCase : ParametrizedUseCase<BookId, Flow<Book>>