package pl.kamilszustak.read.common.resource

import android.app.Application
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val application: Application,
) : ResourceProvider {

    override val resources: Resources
        get() = application.resources

    override fun getBoolean(@BoolRes resourceId: Int): Boolean =
        resources.getBoolean(resourceId)

    override fun getColor(@ColorRes resourceId: Int): Int =
        application.getColor(resourceId)

    override fun getColor(@ColorRes resourceId: Int, theme: Resources.Theme): Int =
        resources.getColor(resourceId, theme)

    override fun getDimension(@DimenRes resourceId: Int): Float =
        resources.getDimension(resourceId)

    override fun getDrawable(resourceId: Int): Drawable =
        application.getDrawable(resourceId)
            ?: throw Resources.NotFoundException("Drawable resource $resourceId not found")

    override fun getDrawable(resourceId: Int, theme: Resources.Theme): Drawable =
        resources.getDrawable(resourceId, theme)

    override fun getFloat(@DimenRes resourceId: Int): Float =
        resources.getFloat(resourceId)

    override fun getFont(@FontRes resourceId: Int): Typeface =
        resources.getFont(resourceId)

    override fun getId(name: String, defaultType: String?, defaultPackage: String?): Int =
        resources.getIdentifier(name, defaultType, defaultPackage)

    override fun getIntArray(@ArrayRes resourceId: Int): IntArray =
        resources.getIntArray(resourceId)

    override fun getInt(@IntegerRes resourceId: Int): Int =
        resources.getInteger(resourceId)

    override fun getPluralString(@PluralsRes resourceId: Int, quantity: Int, vararg arguments: Any): String {
        return if (arguments.isEmpty()) {
            resources.getQuantityString(resourceId, quantity)
        } else {
            resources.getQuantityString(resourceId, quantity, *arguments)
        }
    }

    override fun getString(@StringRes resourceId: Int, vararg arguments: Any): String {
        return if (arguments.isEmpty()) {
            resources.getString(resourceId)
        } else {
            resources.getString(resourceId, *arguments)
        }
    }

    override fun getStringArray(@ArrayRes resourceId: Int): Array<String> =
        resources.getStringArray(resourceId)

    override fun getText(@StringRes resourceId: Int): CharSequence =
        resources.getText(resourceId)

    override fun getPluralText(@PluralsRes resourceId: Int, quantity: Int): CharSequence =
        resources.getQuantityText(resourceId, quantity)

    override fun getTextArray(@ArrayRes resourceId: Int): Array<CharSequence> =
        resources.getTextArray(resourceId)
}