package pl.kamilszustak.read.model.mapper.quote

import pl.kamilszustak.read.model.data.QuoteEntity
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<Quote, QuoteEntity>() {
    override fun map(model: Quote): QuoteEntity =
        QuoteEntity(
            content = model.content,
            author = model.author,
            book = model.book
        ).apply {
            id = model.id
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}