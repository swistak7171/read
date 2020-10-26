package pl.kamilszustak.read.domain.access.usecase.book

import pl.kamilszustak.read.domain.access.usecase.CoroutineParametrizedUseCase
import pl.kamilszustak.read.model.domain.Book

interface AddBookUseCase : CoroutineParametrizedUseCase<Book, Result<Unit>>