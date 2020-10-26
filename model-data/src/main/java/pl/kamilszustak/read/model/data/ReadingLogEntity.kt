package pl.kamilszustak.read.model.data

data class ReadingLogEntity(
    var userId: String = "",
    var bookId: String = "",
    var readPages: Int = 0,
) : Entity() {

    companion object {
        const val COLLECTION_NAME: String = "reading_logs"
        const val USER_ID_PROPERTY: String = "user_id"
        const val BOOK_ID_PROPERTY: String = "book_id"
        const val READ_PAGES_PROPERTY: String = "read_pages"
    }
}
