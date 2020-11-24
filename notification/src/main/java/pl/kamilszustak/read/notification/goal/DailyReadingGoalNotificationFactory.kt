package pl.kamilszustak.read.notification.goal

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import pl.kamilszustak.read.notification.NotificationFactory
import pl.kamilszustak.read.notification.R
import pl.kamilszustak.read.notification.channel.ReadingGoalNotificationChannelFactory

class DailyReadingGoalNotificationFactory : NotificationFactory<DailyReadingGoalNotificationDetails>() {
    override fun create(
        context: Context,
        details: DailyReadingGoalNotificationDetails
    ): Notification {
        val factory = ReadingGoalNotificationChannelFactory()
        val channelId = factory.create(context).id

        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.icon_alarm)
            .setContentTitle("Reading goal")
            .setContentText("You read ${details.progressValue} pages today")
            .setProgress(details.progressMaxValue, details.progressValue, false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    companion object {
        const val NOTIFICATION_ID: Int = 101
    }
}