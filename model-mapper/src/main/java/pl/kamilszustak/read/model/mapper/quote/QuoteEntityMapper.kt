package pl.kamilszustak.read.model.mapper.quote

import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.entity.QuoteEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class QuoteEntityMapper @Inject constructor() : Mapper<QuoteEntity, Quote, Unit>() {
    override fun map(model: QuoteEntity, parameter: Unit): Quote =
        Quote(
            id = QuoteId(model.id),
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            content = model.content,
            author = model.author,
            book = model.book,
            backgroundColorValue = model.backgroundColorValue
        )
}