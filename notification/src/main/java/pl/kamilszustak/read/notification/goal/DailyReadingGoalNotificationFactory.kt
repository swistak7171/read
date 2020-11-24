package pl.kamilszustak.read.notification.goal

import android.app.Application
import android.app.Notification
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.notification.NotificationFactory
import pl.kamilszustak.read.notification.R
import pl.kamilszustak.read.notification.channel.ReadingGoalNotificationChannelFactory
import javax.inject.Inject


class DailyReadingGoalNotificationFactory @Inject constructor(
    private val application: Application,
    private val resourceProvider: ResourceProvider,
    private val channelFactory: ReadingGoalNotificationChannelFactory,
) : NotificationFactory<DailyReadingGoalNotificationDetails>() {

    override fun create(details: DailyReadingGoalNotificationDetails): Notification {
        val channelId = channelFactory.create().id
        val pagesLeft = details.progressMaxValue - details.progressValue
        val title = resourceProvider.getString(R.string.daily_reading_goal_notification_title)
        val text = resourceProvider.getPluralString(
            R.plurals.daily_reading_goal_notification_text,
            pagesLeft,
            pagesLeft
        )

        val bigText = resourceProvider.getPluralString(
            R.plurals.daily_reading_goal_notification_big_text,
            pagesLeft,
            pagesLeft,
            details.progressValue,
            details.progressMaxValue
        )

        val style = NotificationCompat.BigTextStyle()
            .setBigContentTitle(title)
            .bigText(bigText)

        return NotificationCompat.Builder(application, channelId)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.icon_alarm)
            .setProgress(details.progressMaxValue, details.progressValue, false)
            .setStyle(style)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
    }

    companion object {
        const val NOTIFICATION_ID: Int = 101
    }
}