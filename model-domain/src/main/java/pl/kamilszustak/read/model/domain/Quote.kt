package pl.kamilszustak.read.model.domain

data class Quote(
    val content: String,
    val author: String,
    val book: String?
) : Model()