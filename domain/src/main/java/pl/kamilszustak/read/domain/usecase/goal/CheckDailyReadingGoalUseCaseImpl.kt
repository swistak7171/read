package pl.kamilszustak.read.domain.usecase.goal

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import pl.kamilszustak.model.common.ReadingGoalResult
import pl.kamilszustak.read.domain.access.usecase.goal.CheckDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.GetLatestDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.log.GetReadingLogByDateUseCase
import pl.kamilszustak.read.model.domain.LogEntry
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckDailyReadingGoalUseCaseImpl @Inject constructor(
    private val getLatestDailyReadingGoal: GetLatestDailyReadingGoalUseCase,
    private val getReadingLogByDate: GetReadingLogByDateUseCase,
) : CheckDailyReadingGoalUseCase {

    override suspend fun invoke(): ReadingGoalResult? {
        val goalDeferred = coroutineScope { async { getLatestDailyReadingGoal() } }
        val entriesDeferred = coroutineScope { async { getReadingLogByDate(Date()) } }

        val goal = goalDeferred.await() ?: return null
        val entries = entriesDeferred.await()
        val readPages = entries.sumBy(LogEntry::readPages)

        return ReadingGoalResult(
            readPages = readPages,
            pagesToRead = goal.pagesNumber
        )
    }
}