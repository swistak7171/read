package pl.kamilszustak.read.model.domain

import com.google.firebase.database.PropertyName

data class Quote(
    @PropertyName("content")
    val content: String,

    @PropertyName("author")
    val author: String,

    @PropertyName("book")
    val book: String?
) : Model()