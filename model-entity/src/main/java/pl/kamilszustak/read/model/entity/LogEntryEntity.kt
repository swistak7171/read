package pl.kamilszustak.read.model.entity

import com.google.firebase.database.PropertyName

data class LogEntryEntity(
    @get:PropertyName(USER_ID_PROPERTY)
    @set:PropertyName(USER_ID_PROPERTY)
    var userId: String = "",

    @get:PropertyName(BOOK_ID_PROPERTY)
    @set:PropertyName(BOOK_ID_PROPERTY)
    var bookId: String = "",

    @get:PropertyName(START_PAGE_PROPERTY)
    @set:PropertyName(START_PAGE_PROPERTY)
    var startPage: Int = 0,

    @get:PropertyName(END_PAGE_PROPERTY)
    @set:PropertyName(END_PAGE_PROPERTY)
    var endPage: Int = 0,
) : Entity() {

    companion object {
        const val COLLECTION_NAME: String = "reading_log"
        const val USER_ID_PROPERTY: String = "user_id"
        const val BOOK_ID_PROPERTY: String = "book_id"
        const val START_PAGE_PROPERTY: String = "start_page"
        const val END_PAGE_PROPERTY: String = "end_page"
    }
}
