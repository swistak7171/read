package pl.kamilszustak.read.domain.usecase.goal

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import pl.kamilszustak.read.common.date.Time
import pl.kamilszustak.read.domain.access.usecase.goal.SetReadingGoalUseCase
import pl.kamilszustak.read.work.worker.ReadingGoalNotificationWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetReadingGoalUseCaseImpl @Inject constructor(
    private val workManager: WorkManager,
) : SetReadingGoalUseCase {

    override fun invoke(pagesNumber: Int, reminderTime: Time): Result<Unit> {
        val delay = Time.current().toLater(reminderTime)
        val request = PeriodicWorkRequestBuilder<ReadingGoalNotificationWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        // TODO: Change to KEEP
        workManager.enqueueUniquePeriodicWork(
            ReadingGoalNotificationWorker.NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )

        return Result.success(Unit)
    }
}