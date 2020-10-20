package pl.kamilszustak.read.model.domain

enum class IsbnType {
    ISBN_10,
    ISBN_13;

    companion object {
        fun ofValue(value: String): IsbnType =
            when (value.length) {
                10 -> ISBN_10
                13 -> ISBN_13
                else -> throw IllegalArgumentException("Invalid ISBN length: $value")
            }
    }
}