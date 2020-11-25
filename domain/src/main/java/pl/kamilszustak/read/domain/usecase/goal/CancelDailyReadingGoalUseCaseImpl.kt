package pl.kamilszustak.read.domain.usecase.goal

import androidx.work.WorkManager
import pl.kamilszustak.read.domain.access.usecase.goal.CancelDailyReadingGoalUseCase
import pl.kamilszustak.read.work.worker.DailyReadingGoalNotificationWorker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CancelDailyReadingGoalUseCaseImpl @Inject constructor(
    private val workManager: WorkManager,
) : CancelDailyReadingGoalUseCase {

    override fun invoke() {
        workManager.cancelUniqueWork(DailyReadingGoalNotificationWorker.NAME)
    }
}