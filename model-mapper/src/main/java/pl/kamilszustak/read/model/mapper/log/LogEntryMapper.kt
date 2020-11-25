package pl.kamilszustak.read.model.mapper.log

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import pl.kamilszustak.read.model.entity.LogEntryEntity
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class LogEntryMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<LogEntry, LogEntryEntity, Unit>() {

    private val userId: String by lazy { getUser().id.value }

    override fun map(model: LogEntry, parameter: Unit): LogEntryEntity =
        LogEntryEntity(
            userId = userId,
            bookId = model.book.id.value,
            startPage = model.startPage,
            endPage = model.endPage
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}