package pl.kamilszustak.read.model.mapper.log

import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.model.common.id.ReadingLogId
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.data.ReadingLogEntity
import pl.kamilszustak.read.model.domain.ReadingLog
import pl.kamilszustak.read.model.mapper.CoroutineMapper
import javax.inject.Inject

class ReadingLogEntityMapper @Inject constructor(
    private val getBook: GetBookUseCase,
) : CoroutineMapper<ReadingLogEntity, ReadingLog>() {

    override suspend fun mapInternal(model: ReadingLogEntity): ReadingLog {
        val bookId = BookId(model.bookId)
        val book = getBook(bookId) ?: throw IllegalStateException("There is a reading log (${model.id}) of non-existent book (${model.bookId})")
        val readingLogId = ReadingLogId(model.id)

        return ReadingLog(
            id = readingLogId,
            creationDate = model.creationDate,
            modificationDate = model.modificationDate,
            book = book,
            readPages = model.readPages
        )
    }
}