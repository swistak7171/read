package pl.kamilszustak.read.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.getSystemService

abstract class NotificationChannelFactory {
    abstract fun create(context: Context): NotificationChannel

    protected fun getNotificationManager(context: Context): NotificationManager =
        context.getSystemService<NotificationManager>() ?: error("Cannot get NotificationManager system service from context")
}