package pl.kamilszustak.read.model.mapper.log

import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.data.ReadingLogEntity
import pl.kamilszustak.read.model.domain.ReadingLog
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class ReadingLogMapper @Inject constructor(
    getUser: GetUserUseCase,
) : Mapper<ReadingLog, ReadingLogEntity>() {

    private val userId: String by lazy { getUser().uid }

    override fun map(model: ReadingLog): ReadingLogEntity =
        ReadingLogEntity(
            userId = userId,
            bookId = model.book.id.value,
            readPages = model.readPages
        ).apply {
            id = model.id.value
            creationDate = model.creationDate
            modificationDate = model.modificationDate
        }
}