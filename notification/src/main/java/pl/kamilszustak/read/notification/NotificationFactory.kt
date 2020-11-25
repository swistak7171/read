package pl.kamilszustak.read.notification

import android.app.Notification

abstract class NotificationFactory<T> {
    abstract fun create(details: T): Notification
}