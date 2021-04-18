package pl.kamilszustak.read.domain.access.usecase.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase

interface ArchiveBookUseCase : BaseUseCase {
    suspend operator fun invoke(id: BookId): Result<Unit>
}