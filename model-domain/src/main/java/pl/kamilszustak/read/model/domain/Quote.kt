package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
class Quote(
    override val id: String,
    override val creationDate: Date,
    override val modificationDate: Date,
    val content: String,
    val author: String,
    val book: String?
) : Model()