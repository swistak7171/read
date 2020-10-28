package pl.kamilszustak.read.domain.access.usecase.book

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.read.domain.access.usecase.UseCase
import pl.kamilszustak.read.model.domain.Book

interface ObserveAllBooksUseCase : UseCase<Flow<List<Book>>>