package pl.kamilszustak.read.notification.goal

import pl.kamilszustak.read.notification.NotificationDetails

data class DailyReadingGoalNotificationDetails(
    val progressMaxValue: Int,
    val progressValue: Int,
) : NotificationDetails()