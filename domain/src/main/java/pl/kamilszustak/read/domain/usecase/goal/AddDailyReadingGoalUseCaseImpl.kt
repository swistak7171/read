package pl.kamilszustak.read.domain.usecase.goal

import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.data.access.repository.ReadingGoalRepository
import pl.kamilszustak.read.domain.access.usecase.goal.AddDailyReadingGoalUseCase
import pl.kamilszustak.read.model.domain.ReadingGoal
import pl.kamilszustak.read.model.entity.goal.ReadingGoalType
import pl.kamilszustak.read.model.mapper.goal.ReadingGoalMapper
import pl.kamilszustak.read.work.worker.DailyReadingGoalNotificationWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddDailyReadingGoalUseCaseImpl @Inject constructor(
    private val repository: ReadingGoalRepository,
    private val workManager: WorkManager,
    private val mapper: ReadingGoalMapper,
) : AddDailyReadingGoalUseCase {

    override suspend fun invoke(input: ReadingGoal): Result<Unit> {
        val entity = mapper.map(input, ReadingGoalType.DAILY)
        repository.add(entity)

        val delay = Time.current().toLater(input.reminderTime)
        val request = PeriodicWorkRequestBuilder<DailyReadingGoalNotificationWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

//        workManager.enqueueUniquePeriodicWork(
//            ReadingGoalNotificationWorker.NAME,
//            ExistingPeriodicWorkPolicy.REPLACE,
//            request
//        )
        workManager.enqueue(request)

        return Result.success(Unit)
    }
}