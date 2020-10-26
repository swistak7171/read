package pl.kamilszustak.read.model.domain

import pl.kamilszustak.model.common.id.ModelId
import java.util.*

data class ReadingLog(
    override val id: ModelId,
    override val creationDate: Date,
    override val modificationDate: Date,
    val book: Book,
    val readPages: Int,
) : Model()
