package pl.kamilszustak.read.notification.channel

import android.app.Application
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.notification.NotificationChannelFactory
import pl.kamilszustak.read.notification.R
import javax.inject.Inject

class ReadingGoalNotificationChannelFactory @Inject constructor(
    application: Application,
    private val resourceProvider: ResourceProvider,
) : NotificationChannelFactory(application) {

    override val name: String
        get() = resourceProvider.getString(R.string.reading_goal_notification_channel_name)

    override val id: String
        get() = CHANNEL_ID

    companion object {
        const val CHANNEL_ID: String = "reading_goal_notification_channel"
    }
}