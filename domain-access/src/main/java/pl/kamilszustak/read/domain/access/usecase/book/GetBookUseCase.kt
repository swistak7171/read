package pl.kamilszustak.read.domain.access.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.Book

interface GetBookUseCase : CoroutineParametrizedUseCase<BookId, Book?>