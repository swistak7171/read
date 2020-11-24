package pl.kamilszustak.read.work.worker

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.notification.goal.DailyReadingGoalNotificationDetails
import pl.kamilszustak.read.notification.goal.DailyReadingGoalNotificationFactory
import pl.kamilszustak.read.work.ListenableWorkerFactory

class DailyReadingGoalNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val notificationFactory: DailyReadingGoalNotificationFactory,
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        val details = DailyReadingGoalNotificationDetails(
            progressMaxValue = 100,
            progressValue = 50
        )
        val notification = notificationFactory.create(details)
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