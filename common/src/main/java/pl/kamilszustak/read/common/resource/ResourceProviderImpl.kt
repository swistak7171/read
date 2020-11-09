package pl.kamilszustak.read.common.resource

import android.app.Application
import android.content.res.Resources
import android.graphics.Typeface
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val application: Application,
) : ResourceProvider {

    private inline val resources: Resources
        get() = application.resources

    init {
    }

    override fun getBoolean(resourceId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getColor(resourceId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getColor(resourceId: Int, theme: Resources.Theme): Int {
        TODO("Not yet implemented")
    }

    override fun getDimension(resourceId: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getFloat(resourceId: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getFont(resourceId: Int): Typeface {
        TODO("Not yet implemented")
    }

    override fun getFraction(resourceId: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getIdentifier(resourceId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getIntArray(resourceId: Int): Array<Int> {
        TODO("Not yet implemented")
    }

    override fun getInt(resourceId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getPluralString(resourceId: Int, quantity: Int, vararg arguments: Any): String {
        TODO("Not yet implemented")
    }

    override fun getString(@StringRes resourceId: Int, vararg arguments: Any): String {
        return if (arguments.isEmpty()) {
            resources.getString(resourceId)
        } else {
            resources.getString(resourceId, *arguments)
        }
    }

    override fun getStringArray(resourceId: Int): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getText(resourceId: Int, vararg arguments: Any): CharSequence {
        TODO("Not yet implemented")
    }

    override fun getTextArray(resourceId: Int): Array<CharSequence> {
        TODO("Not yet implemented")
    }
}