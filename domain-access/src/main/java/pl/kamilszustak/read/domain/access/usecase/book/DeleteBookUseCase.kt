package pl.kamilszustak.read.domain.access.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface DeleteBookUseCase : CoroutineParametrizedUseCase<BookId, Result<Unit>>