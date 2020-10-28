package pl.kamilszustak.read.model.mapper.quote

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.QuoteEntity
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class QuoteMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<Quote, QuoteEntity>() {

    private val userId: String by lazy { getUser().uid }

    override fun map(model: Quote): QuoteEntity =
        QuoteEntity(
            content = model.content,
            author = model.author,
            book = model.book,
            userId = userId
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}