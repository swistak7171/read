package pl.kamilszustak.read.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.getSystemService
import pl.kamilszustak.read.notification.channel.ReadingGoalNotificationChannelFactory

abstract class NotificationChannelFactory(
    protected val context: Context,
) {
    abstract val name: String
    abstract val id: String

    open val importance: Int
        get() = NotificationManager.IMPORTANCE_DEFAULT

    fun create(): NotificationChannel {
        return NotificationChannel(id, name, importance).also { channel ->
            notificationManager.createNotificationChannel(channel)
        }
    }

    val notificationManager: NotificationManager
        get() = context.getSystemService() ?: error("Cannot get NotificationManager system service from context")
}