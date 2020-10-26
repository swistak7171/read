package pl.kamilszustak.read.domain.access.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.Book

interface EditBookUseCase : BaseUseCase {
    suspend operator fun invoke(id: BookId, edit: (Book) -> Book): Result<Unit>
}