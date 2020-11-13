package pl.kamilszustak.read.ui.main.scanner.selection

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.main.R

enum class TextSelectionMode(
    @StringRes val nameResourceId: Int,
) {
    BLOCKS(R.string.text_selection_mode_blocks),
    LINES(R.string.text_selection_mode_lines),
    WORDS(R.string.text_sleection_mode_words);

    companion object {
        val default: TextSelectionMode
            get() = LINES
    }
}