package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.LogEntryId
import java.util.*

@Parcelize
data class LogEntry(
    override val id: LogEntryId = LogEntryId(),
    override val creationDate: Date = Date(),
    override val modificationDate: Date = Date(),
    val book: Book,
    val readPages: Int,
) : Model()
