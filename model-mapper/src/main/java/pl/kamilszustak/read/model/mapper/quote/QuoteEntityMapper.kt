package pl.kamilszustak.read.model.mapper.quote

import pl.kamilszustak.read.model.data.QuoteEntity
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class QuoteEntityMapper @Inject constructor() : Mapper<QuoteEntity, Quote>() {
    override fun map(model: QuoteEntity): Quote =
        Quote(
            id = model.id,
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            content = model.content,
            author = model.author,
            book = model.book
        )
}