package pl.kamilszustak.read.model.mapper.quote

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.entity.QuoteEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class QuoteMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<Quote, QuoteEntity, Unit>() {

    private val userId: String by lazy { getUser().id.value }

    override fun map(model: Quote, parameter: Unit): QuoteEntity =
        QuoteEntity(
            content = model.content,
            author = model.author,
            book = model.book,
            backgroundColorValue = model.backgroundColorValue,
            userId = userId
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}