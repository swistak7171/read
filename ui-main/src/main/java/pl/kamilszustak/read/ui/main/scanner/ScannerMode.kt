package pl.kamilszustak.read.ui.main.scanner

enum class ScannerMode {
    ISBN,
    QUOTE;

    companion object {
        val default: ScannerMode
            get() = ISBN
    }
}