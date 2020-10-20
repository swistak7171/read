package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.CollectionBookEntity
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class CollectionBookMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<CollectionBook, CollectionBookEntity>() {

    private val userId: String = getUser().uid

    override fun map(model: CollectionBook): CollectionBookEntity =
        CollectionBookEntity(
            volumeId = model.volumeId?.value,
            title = model.title,
            author = model.author,
            pagesNumber = model.pagesNumber,
            publicationDate = model.publicationDate,
            isbn = model.isbn,
            description = model.description,
            userId = userId,
            coverImageUrl = model.coverImageUrl,
            readPages = model.readPages
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}