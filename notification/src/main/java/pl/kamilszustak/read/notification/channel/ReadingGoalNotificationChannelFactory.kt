package pl.kamilszustak.read.notification.channel

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.notification.NotificationChannelFactory
import pl.kamilszustak.read.notification.R
import javax.inject.Inject

class ReadingGoalNotificationChannelFactory @Inject constructor(
    private val application: Application,
    private val resourceProvider: ResourceProvider,
) : NotificationChannelFactory() {

    override fun create(): NotificationChannel {
        val manager = getNotificationManager(application)
        val name = resourceProvider.getString(R.string.reading_goal_notification_channel_name)
        val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        return channel
    }

    companion object {
        const val CHANNEL_ID: String = "reading_goal_notification_channel"
    }
}