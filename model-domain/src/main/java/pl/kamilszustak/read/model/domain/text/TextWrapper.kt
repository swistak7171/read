package pl.kamilszustak.read.model.domain.text

data class TextWrapper(
    val blocks: List<TextBlock>,
) {
    val blockWrappers: List<TextBlockWrapper> by lazy {
        blocks.map { TextBlockWrapper(it) }
    }

    val text: String by lazy {
        buildString {
            blocks.forEach { block ->
                append(System.lineSeparator())
                append(block.value)
            }
        }
    }
}