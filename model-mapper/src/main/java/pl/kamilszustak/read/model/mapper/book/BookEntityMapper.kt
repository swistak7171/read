package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.model.common.id.VolumeId
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.entity.BookEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class BookEntityMapper @Inject constructor() : Mapper<BookEntity, Book, Unit>() {
    override fun map(model: BookEntity, parameter: Unit): Book {
        val entityVolumeId = model.volumeId
        val volumeId = entityVolumeId.useOrNull { VolumeId(it) }

        return Book(
            id = BookId(model.id),
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            volumeId = volumeId,
            title = model.title,
            author = model.author,
            pagesNumber = model.pagesNumber,
            publicationDate = model.publicationDate,
            isbn = model.isbn,
            description = model.description,
            coverImageUrl = model.coverImageUrl,
            readPages = model.readPages
        )
    }
}