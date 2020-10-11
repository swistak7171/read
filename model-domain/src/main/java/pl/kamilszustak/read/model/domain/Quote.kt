package pl.kamilszustak.read.model.domain

import com.google.firebase.database.PropertyName

data class Quote(
    @get:PropertyName(CONTENT_PROPERTY)
    @set:PropertyName(CONTENT_PROPERTY)
    var content: String,

    @get:PropertyName(AUTHOR_PROPERTY)
    @set:PropertyName(AUTHOR_PROPERTY)
    var author: String,

    @get:PropertyName(BOOK_PROPERTY)
    @set:PropertyName(BOOK_PROPERTY)
    var book: String?
) : Entity() {

    companion object {
        const val CONTENT_PROPERTY: String = "content"
        const val AUTHOR_PROPERTY: String = "author"
        const val BOOK_PROPERTY: String = "book"
    }
}