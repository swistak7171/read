package pl.kamilszustak.read.model.domain

import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class CollectionBook(
    override val id: String,
    override val creationDate: Date,
    override val modificationDate: Date,
    val title: String,
    val author: String,
    val numberOfPages: Int,
    val publicationDate: Date?,
    val isbn: String,
    val description: String?
) : Model()