package pl.kamilszustak.read.model.domain

import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize
import pl.kamilszustak.model.common.id.QuoteId
import java.util.*

@Parcelize
data class Quote(
    override val id: QuoteId = QuoteId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val content: String,
    val author: String,
    val book: String?,
    @ColorInt val backgroundColorValue: Int,
) : Model() {

    val overview: String
        get() = buildString {
            append("\"")
            append(content)
            append("\"")
            appendLine()
            append("~")
            append(author)
        }
}