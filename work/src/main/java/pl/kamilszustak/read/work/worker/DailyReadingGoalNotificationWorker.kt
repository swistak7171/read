package pl.kamilszustak.read.work.worker

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull
import pl.kamilszustak.read.domain.access.storage.SettingsStorage
import pl.kamilszustak.read.domain.access.usecase.goal.CheckDailyReadingGoalCompletionUseCase
import pl.kamilszustak.read.notification.goal.DailyReadingGoalNotificationFactory
import pl.kamilszustak.read.work.ListenableWorkerFactory

class DailyReadingGoalNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val notificationFactory: DailyReadingGoalNotificationFactory,
    private val checkDailyReadingGoalCompletion: CheckDailyReadingGoalCompletionUseCase,
    private val settingsStorage: SettingsStorage,
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        val isEnabled = settingsStorage.isDailyReadingGoalEnabled.asFlow()
            .firstOrNull() ?: false

        if (!isEnabled) {
            return Result.failure()
        }

        val result = checkDailyReadingGoalCompletion() ?: return Result.failure()
        val notification = notificationFactory.create(result)
        val notificationId = DailyReadingGoalNotificationFactory.NOTIFICATION_ID
        val manager = NotificationManagerCompat.from(applicationContext)

        with(manager) {
            cancel(notificationId)
            notify(notificationId, notification)
        }

        return Result.success()
     }

    companion object {
        const val NAME: String = "reading_goal_notification_worker"
    }

    @AssistedInject.Factory
    interface Factory : ListenableWorkerFactory
}