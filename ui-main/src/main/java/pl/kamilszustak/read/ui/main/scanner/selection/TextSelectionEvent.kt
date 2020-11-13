package pl.kamilszustak.read.ui.main.scanner.selection

import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class TextSelectionEvent : ViewEvent {
    object OnTextSelectionModeButtonClicked : TextSelectionEvent()

    data class OnTextSelectionModeSelected(
        val selectionIndex: Int,
    ) : TextSelectionEvent()
}
