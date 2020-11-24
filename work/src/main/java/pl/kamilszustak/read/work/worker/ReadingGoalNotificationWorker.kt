package pl.kamilszustak.read.work.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.graphics.drawable.Icon
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.IconCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.work.ListenableWorkerFactory
import pl.kamilszustak.read.work.R
import timber.log.Timber

class ReadingGoalNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        Timber.i("THIS IS NOTIFICATION")

        val manager = applicationContext.getSystemService<NotificationManager>() ?: return Result.failure()
        val channelId = "reading_goal_notification_channel"
        val channel = NotificationChannel(channelId, "name", NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        val icon = IconCompat.createWithResource(applicationContext, R.drawable.flag_pl)
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.common_full_open_on_phone)
            .setContentTitle("Reading goal")
            .setContentText("You read 5 pages today")
            .setProgress(100, 50, false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(123, notification)
        }

        return Result.success()
    }

    companion object {
        const val NAME: String = "reading_goal_notification_worker"
    }

    @AssistedInject.Factory
    interface Factory : ListenableWorkerFactory
}