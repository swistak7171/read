package pl.kamilszustak.read.ui.main.scanner.selection

import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class TextSelectionAction : ViewAction {
    data class ShowTextSelectionModeDialog(
        val itemsResources: List<Int>,
        val initialSelection: Int,
    ) : TextSelectionAction()
}
