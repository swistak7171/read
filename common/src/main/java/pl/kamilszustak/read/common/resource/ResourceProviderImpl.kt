package pl.kamilszustak.read.common.resource

import android.app.Application
import android.content.res.Resources
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val application: Application,
) : ResourceProvider {

    private inline val resources: Resources
        get() = application.resources

    init {
        resources.getStringArray()
        resources.getBoolean()
        resources.getColor()
        resources.getDimension()
        resources.getDrawable()
        resources.getFloat()
        resources.getFont()
        resources.getFraction()
        resources.getIdentifier()
        resources.getIntArray()
        resources.getInteger()
        resources.get
    }

    override fun getString(@StringRes resourceId: Int, vararg arguments: Any): String {
        return if (arguments.isEmpty()) {
            resources.getString(resourceId)
        } else {
            resources.getString(resourceId, *arguments)
        }
    }
}