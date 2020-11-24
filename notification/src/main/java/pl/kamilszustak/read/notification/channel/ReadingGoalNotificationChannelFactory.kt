package pl.kamilszustak.read.notification.channel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import pl.kamilszustak.read.notification.NotificationChannelFactory
import pl.kamilszustak.read.notification.R

class ReadingGoalNotificationChannelFactory : NotificationChannelFactory() {
    override fun create(context: Context): NotificationChannel {
        val manager = getNotificationManager(context)
        val name = context.getString(R.string.reading_goal_notification_channel_name)
        val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        return channel
    }

    companion object {
        const val CHANNEL_ID: String = "reading_goal_notification_channel"
    }
}