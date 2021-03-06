package pl.kamilszustak.read.model.entity

import com.google.firebase.database.PropertyName

data class QuoteEntity(
    @get:PropertyName(CONTENT_PROPERTY)
    @set:PropertyName(CONTENT_PROPERTY)
    var content: String = "",

    @get:PropertyName(AUTHOR_PROPERTY)
    @set:PropertyName(AUTHOR_PROPERTY)
    var author: String = "",

    @get:PropertyName(BOOK_PROPERTY)
    @set:PropertyName(BOOK_PROPERTY)
    var book: String? = "",

    @get:PropertyName(BACKGROUND_COLOR_VALUE_PROPERTY)
    @set:PropertyName(BACKGROUND_COLOR_VALUE_PROPERTY)
    var backgroundColorValue: Int = -1,

    @get:PropertyName(USER_ID_PROPERTY)
    @set:PropertyName(USER_ID_PROPERTY)
    var userId: String = "",
) : Entity() {

    companion object {
        const val COLLECTION_NAME: String = "quotes"
        const val CONTENT_PROPERTY: String = "content"
        const val AUTHOR_PROPERTY: String = "author"
        const val BOOK_PROPERTY: String = "book"
        const val BACKGROUND_COLOR_VALUE_PROPERTY: String = "background_color"
        const val USER_ID_PROPERTY: String = "user_id"
    }
}