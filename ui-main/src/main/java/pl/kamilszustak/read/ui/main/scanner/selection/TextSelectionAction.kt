package pl.kamilszustak.read.ui.main.scanner.selection

import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class TextSelectionAction : ViewAction {
    object InvalidateImageView : TextSelectionAction()

    data class ShowTextSelectionModeDialog(
        val itemsResources: List<Int>,
        val initialSelection: Int,
    ) : TextSelectionAction()

    data class Error(
        val messageResourceId: Int? = null,
        val throwable: Throwable? = null,
    ) : TextSelectionAction()

    data class NavigateToQuoteEditFragment(
        val content: String,
    ) : TextSelectionAction()
}
