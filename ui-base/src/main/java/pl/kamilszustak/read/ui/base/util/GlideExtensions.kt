package pl.kamilszustak.read.ui.base.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}