package pl.kamilszustak.read.ui.base.service

import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import pl.kamilszustak.read.notification.firebase.FirebaseCloudMessagingNotificationDetails
import pl.kamilszustak.read.notification.firebase.FirebaseCloudMessegingNotificationFactory
import javax.inject.Inject

class FirebaseCloudMessagingService : FirebaseMessagingService() {
    @Inject protected lateinit var notificationFactory: FirebaseCloudMessegingNotificationFactory

    override fun onNewToken(token: String) {
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification?.title ?: ""
        val text = message.notification?.body ?: ""
        val details = FirebaseCloudMessagingNotificationDetails(title, text)
        val notification = notificationFactory.create(details)
        val notificationId = FirebaseCloudMessegingNotificationFactory.NOTIFICATION_ID
        val manager = NotificationManagerCompat.from(applicationContext)

        with(manager) {
            cancel(notificationId)
            notify(notificationId, notification)
        }
    }
}