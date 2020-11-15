package pl.kamilszustak.read.common.resource

import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*

interface ResourceProvider {
    val resources: Resources

    fun getBoolean(@BoolRes resourceId: Int): Boolean
    fun getColor(@ColorRes resourceId: Int): Int
    fun getColor(@ColorRes resourceId: Int, theme: Resources.Theme): Int
    fun getDimension(@DimenRes resourceId: Int): Float
    fun getDrawable(@DrawableRes resourceId: Int): Drawable
    fun getDrawable(@DrawableRes resourceId: Int, theme: Resources.Theme): Drawable
    fun getFloat(@DimenRes resourceId: Int): Float
    fun getFont(@FontRes resourceId: Int): Typeface
    fun getId(name: String, defaultType: String? = null, defaultPackage: String? = null): Int
    fun getIntArray(@ArrayRes resourceId: Int): IntArray
    fun getInt(@IntegerRes resourceId: Int): Int
    fun getPluralString(@PluralsRes resourceId: Int, quantity: Int, vararg arguments: Any): String
    fun getString(@StringRes resourceId: Int, vararg arguments: Any): String
    fun getStringArray(@ArrayRes resourceId: Int): Array<String>
    fun getText(@StringRes resourceId: Int): CharSequence
    fun getPluralText(@PluralsRes resourceId: Int, quantity: Int): CharSequence
    fun getTextArray(@ArrayRes resourceId: Int): Array<CharSequence>
}