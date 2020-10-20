package pl.kamilszustak.model.common

enum class VolumeSearchParameterType(
    val queryName: String,
) {
    GENERAL(""),
    TITLE("intitle"),
    AUTHOR("inauthor"),
    ISBN("isbn")
}