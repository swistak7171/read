package pl.kamilszustak.read.model.data

import com.google.firebase.database.PropertyName

data class LogEntryEntity(
    @get:PropertyName(USER_ID_PROPERTY)
    @set:PropertyName(USER_ID_PROPERTY)
    var userId: String = "",

    @get:PropertyName(BOOK_ID_PROPERTY)
    @set:PropertyName(BOOK_ID_PROPERTY)
    var bookId: String = "",

    @get:PropertyName(READ_PAGES_PROPERTY)
    @set:PropertyName(READ_PAGES_PROPERTY)
    var readPages: Int = 0,
) : Entity() {

    companion object {
        const val COLLECTION_NAME: String = "reading_log"
        const val USER_ID_PROPERTY: String = "user_id"
        const val BOOK_ID_PROPERTY: String = "book_id"
        const val READ_PAGES_PROPERTY: String = "read_pages"
    }
}
