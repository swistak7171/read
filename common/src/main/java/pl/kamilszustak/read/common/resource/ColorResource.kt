package pl.kamilszustak.read.common.resource

import android.content.Context
import androidx.annotation.ColorRes

data class ColorResource(
    @ColorRes override val id: Int
) : ApplicationResource<Int> {

    override fun get(context: Context): Int =
        context.getColor(id)
}