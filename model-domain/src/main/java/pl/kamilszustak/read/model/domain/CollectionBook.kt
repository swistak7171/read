package pl.kamilszustak.read.model.domain

import java.util.Date

data class CollectionBook(
    val title: String,
    val author: String,
    val numberOfPages: Int,
    val publicationDate: Date?,
    val isbn: String?,
    val description: String?,
) : Model()