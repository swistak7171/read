package pl.kamilszustak.read.common.resource

import android.content.res.Resources
import android.graphics.Typeface
import androidx.annotation.*

interface ResourceProvider {
    fun getBoolean(@BoolRes resourceId: Int): Boolean
    fun getColor(@ColorRes resourceId: Int): Int
    fun getColor(@ColorRes resourceId: Int, theme: Resources.Theme): Int
    fun getDimension(@DimenRes resourceId: Int): Float
    fun getFloat(@DimenRes resourceId: Int): Float
    fun getFont(@FontRes resourceId: Int): Typeface
    fun getFraction(@FractionRes resourceId: Int): Float
    fun getIdentifier(@IdRes resourceId: Int): Int
    fun getIntArray(@ArrayRes resourceId: Int): Array<Int>
    fun getInt(@IntegerRes resourceId: Int): Int
    fun getPluralString(@PluralsRes resourceId: Int, quantity: Int, vararg arguments: Any): String
    fun getString(@StringRes resourceId: Int, vararg arguments: Any): String
    fun getStringArray(@ArrayRes resourceId: Int): Array<String>
    fun getText(@StringRes resourceId: Int, vararg arguments: Any): CharSequence
    fun getTextArray(@ArrayRes resourceId: Int): Array<CharSequence>
}