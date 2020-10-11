package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.read.model.data.CollectionBookEntity
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class CollectionBookMapper @Inject constructor() : Mapper<CollectionBook, CollectionBookEntity>() {
    override fun map(model: CollectionBook): CollectionBookEntity =
        CollectionBookEntity(
            title = model.title,
            author = model.author,
            numberOfPages = model.numberOfPages,
            publicationDate = model.publicationDate,
            isbn = model.isbn,
            description = model.description
        ).apply {
            id = model.id
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}