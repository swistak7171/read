package pl.kamilszustak.read.model.data

import com.google.firebase.database.PropertyName
import java.util.Date

data class CollectionBookEntity(
    @get:PropertyName(TITLE_PROPERTY)
    @set:PropertyName(TITLE_PROPERTY)
    var title: String,

    @get:PropertyName(AUTHOR_PROPERTY)
    @set:PropertyName(AUTHOR_PROPERTY)
    var author: String,

    @get:PropertyName(NUMBER_OF_PAGES_PROPERTY)
    @set:PropertyName(NUMBER_OF_PAGES_PROPERTY)
    var numberOfPages: Int,

    @get:PropertyName(PUBLICATION_DATE_PROPERTY)
    @set:PropertyName(PUBLICATION_DATE_PROPERTY)
    var publicationDate: Date?,

    @get:PropertyName(ISBD_PROPERTY)
    @set:PropertyName(ISBD_PROPERTY)
    var isbn: String?,

    @get:PropertyName(DESCRIPTION_PROPERTY)
    @set:PropertyName(DESCRIPTION_PROPERTY)
    var description: String?,

    @get:PropertyName(DESCRIPTION_PROPERTY)
    @set:PropertyName(DESCRIPTION_PROPERTY)
    var userId: String,
) : Entity() {

    companion object {
        const val TITLE_PROPERTY: String = "title"
        const val AUTHOR_PROPERTY: String = "author"
        const val NUMBER_OF_PAGES_PROPERTY: String = "number_of_pages"
        const val PUBLICATION_DATE_PROPERTY: String = "publication_date"
        const val ISBD_PROPERTY: String = "isbn"
        const val DESCRIPTION_PROPERTY: String = "description"
        const val USER_ID_PROPERTY: String = "user_id"
    }
}