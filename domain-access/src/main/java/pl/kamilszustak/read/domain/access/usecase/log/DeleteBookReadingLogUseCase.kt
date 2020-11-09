package pl.kamilszustak.read.domain.access.usecase.log

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase

interface DeleteBookReadingLogUseCase : CoroutineParametrizedUseCase<BookId, Result<Unit>>