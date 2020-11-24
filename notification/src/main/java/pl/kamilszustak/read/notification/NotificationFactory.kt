package pl.kamilszustak.read.notification

import android.app.Notification
import android.content.Context

abstract class NotificationFactory<T : NotificationDetails> {
    abstract fun create(context: Context, details: T): Notification
}