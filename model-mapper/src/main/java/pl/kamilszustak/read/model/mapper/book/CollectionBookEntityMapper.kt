package pl.kamilszustak.read.model.mapper.book

import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.model.common.id.VolumeId
import pl.kamilszustak.read.model.data.CollectionBookEntity
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class CollectionBookEntityMapper @Inject constructor() : Mapper<CollectionBookEntity, CollectionBook>() {
    override fun map(model: CollectionBookEntity): CollectionBook {
        val entityVolumeId = model.volumeId
        val volumeId = if (entityVolumeId != null) VolumeId(entityVolumeId) else null

        return CollectionBook(
            id = CollectionBookId(model.id),
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