package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.QuoteId
import java.util.Date

@Parcelize
data class Quote(
    override val id: QuoteId = QuoteId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val content: String,
    val author: String,
    val book: String?
) : Model()