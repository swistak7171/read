package pl.kamilszustak.read.model.domain

import android.graphics.drawable.Drawable

data class Country(
    val name: String,
    val code: String,
    val extension: String,
    val flagDrawable: Drawable?
)