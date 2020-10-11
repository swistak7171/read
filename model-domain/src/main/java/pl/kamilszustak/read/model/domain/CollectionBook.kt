package pl.kamilszustak.read.model.domain

import com.google.firebase.database.PropertyName
import java.util.Date

data class CollectionBook(
    @PropertyName("title")
    val title: String,

    @PropertyName("author")
    val author: String,

    @PropertyName("number_of_pages")
    val numberOfPages: Int,

    @PropertyName("publication_date")
    val publicationDate: Date?,

    @PropertyName("isbn")
    val isbn: String?,

    @PropertyName("description")
    val description: String?,
) : Model()