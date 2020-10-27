package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.model.common.id.ReadingLogId
import java.util.*

@Parcelize
data class LogEntry(
    override val id: ReadingLogId,
    override val creationDate: Date,
    override val modificationDate: Date,
    val book: Book,
    val readPages: Int,
) : Model()
