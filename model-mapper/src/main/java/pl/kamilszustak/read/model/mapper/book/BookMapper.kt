package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.entity.BookEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class BookMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<Book, BookEntity, Unit>() {

    private val userId: String by lazy { getUser().id.value }

    override fun map(model: Book, parameter: Unit): BookEntity =
        BookEntity(
            volumeId = model.volumeId?.value,
            title = model.title,
            author = model.author,
            pagesNumber = model.pagesNumber,
            publicationDate = model.publicationDate,
            isbn = model.isbn,
            description = model.description,
            userId = userId,
            coverImageUrl = model.coverImageUrl,
            isArchived = model.isArchived,
            readPages = model.readPages
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}