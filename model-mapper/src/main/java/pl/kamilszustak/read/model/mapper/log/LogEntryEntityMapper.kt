package pl.kamilszustak.read.model.mapper.log

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.model.common.id.LogEntryId
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.entity.LogEntryEntity
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.mapper.CoroutineMapper
import javax.inject.Inject

class LogEntryEntityMapper @Inject constructor(
    private val getBook: GetBookUseCase,
) : CoroutineMapper<LogEntryEntity, LogEntry>() {

    override suspend fun mapInternal(model: LogEntryEntity): LogEntry {
        val bookId = BookId(model.bookId)
        val book = getBook(bookId) ?: throw IllegalStateException("There is a reading log (${model.id}) of non-existent book (${model.bookId})")
        val readingLogId = LogEntryId(model.id)

        return LogEntry(
            id = readingLogId,
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            book = book,
            startPage = model.startPage,
            endPage = model.endPage
        )
    }
}