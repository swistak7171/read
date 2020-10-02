package pl.kamilszustak.read.data.model

import android.graphics.drawable.Drawable

data class Country(
    val name: String,
    val code: String,
    val extension: String,
    val flagDrawable: Drawable
)