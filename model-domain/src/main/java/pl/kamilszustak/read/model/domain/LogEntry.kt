package pl.kamilszustak.read.model.domain

import kotlinx.parcelize.Parcelize
import pl.kamilszustak.model.common.id.LogEntryId
import java.util.*

@Parcelize
data class LogEntry(
    override val id: LogEntryId = LogEntryId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val book: Book,
    val startPage: Int,
    val endPage: Int,
) : Model() {

    val readPages: Int
        get() = (endPage - startPage)
}
