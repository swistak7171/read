package pl.kamilszustak.read.ui.base.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(imageUrl: String, placeholder: Drawable? = null) {
    var loader = Glide.with(context)
        .load(imageUrl)
        .placeholder(placeholder)
        .into(this)
}