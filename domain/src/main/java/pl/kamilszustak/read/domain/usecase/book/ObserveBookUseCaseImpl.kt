package pl.kamilszustak.read.domain.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.domain.access.usecase.book.ObserveBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.mapper.book.BookEntityMapper
import javax.inject.Inject

class ObserveBookUseCaseImpl @Inject constructor(
    private val repository: BookRepository,
    private val mapper: BookEntityMapper,
) : ObserveBookUseCase {

    override suspend fun invoke(input: BookId): Book? =
        repository.getById(input.value)
            .useOrNull { mapper.map(it) }
}