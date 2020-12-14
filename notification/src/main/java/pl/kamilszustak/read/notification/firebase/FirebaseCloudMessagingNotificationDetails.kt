package pl.kamilszustak.read.notification.firebase

import pl.kamilszustak.read.notification.NotificationDetails

data class FirebaseCloudMessagingNotificationDetails(
    val title: String,
    val text: String,
) : NotificationDetails()