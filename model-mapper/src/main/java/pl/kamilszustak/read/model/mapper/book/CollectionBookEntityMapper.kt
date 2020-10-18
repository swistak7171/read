package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.model.data.CollectionBookEntity
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class CollectionBookEntityMapper @Inject constructor() : Mapper<CollectionBookEntity, CollectionBook>() {
    override fun map(model: CollectionBookEntity): CollectionBook =
        CollectionBook(
            id = CollectionBookId(model.id),
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            title = model.title,
            author = model.author,
            numberOfPages = model.numberOfPages,
            publicationDate = model.publicationDate,
            isbn = model.isbn,
            description = model.description,
            coverImageUrl = model.coverImageUrl,
            readPages = model.readPages
        )
}