package pl.kamilszustak.read.notification.firebase

import android.app.Application
import android.app.Notification
import androidx.core.app.NotificationCompat
import pl.kamilszustak.read.notification.NotificationFactory
import pl.kamilszustak.read.notification.R
import pl.kamilszustak.read.notification.channel.MainNotificationChannelFactory
import javax.inject.Inject

class FirebaseCloudMessegingNotificationFactory @Inject constructor(
    private val application: Application,
    private val channelFactory: MainNotificationChannelFactory,
) : NotificationFactory<FirebaseCloudMessagingNotificationDetails>() {

    override fun create(details: FirebaseCloudMessagingNotificationDetails): Notification {
        val channelId = channelFactory.create().id

        return NotificationCompat.Builder(application, channelId)
            .setContentTitle(details.title)
            .setContentText(details.text)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
    }

    companion object {
        const val NOTIFICATION_ID: Int = 100
    }
}