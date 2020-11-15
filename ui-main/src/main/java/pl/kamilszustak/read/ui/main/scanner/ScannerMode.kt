package pl.kamilszustak.read.ui.main.scanner

enum class ScannerMode(
    val index: Int,
) {
    ISBN(0),
    QUOTE(1);

    companion object {
        val default: ScannerMode
            get() = ISBN
    }
}