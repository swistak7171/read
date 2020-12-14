package pl.kamilszustak.read.notification.channel

import android.app.Application
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.notification.NotificationChannelFactory
import pl.kamilszustak.read.notification.R
import javax.inject.Inject

class MainNotificationChannelFactory @Inject constructor(
    application: Application,
    private val resourceProvider: ResourceProvider,
) : NotificationChannelFactory(application) {

    override val name: String
        get() = resourceProvider.getString(R.string.main_notification_channel_name)

    override val id: String
        get() = CHANNEL_ID

    companion object {
        const val CHANNEL_ID: String = "main_notification_channel"
    }
}