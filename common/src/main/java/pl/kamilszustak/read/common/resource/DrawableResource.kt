package pl.kamilszustak.read.common.resource

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class DrawableResource(
    @DrawableRes override val id: Int
) : ApplicationResource<Drawable> {

    override fun get(context: Context): Drawable =
        context.getDrawable(id) ?: throw Resources.NotFoundException("Drawable not found: $id")
}