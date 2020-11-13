package pl.kamilszustak.read.ui.main.scanner.selection

import android.util.Size
import android.view.MotionEvent
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class TextSelectionEvent : ViewEvent {
    object OnTextSelectionModeButtonClicked : TextSelectionEvent()
    object OnTextRecognitionButtonClicked : TextSelectionEvent()
    object OnRestoreImageButtonClicked : TextSelectionEvent()

    data class OnTextSelectionModeSelected(
        val selectionIndex: Int,
    ) : TextSelectionEvent()

    data class OnImageViewTouch(
        val motionEvent: MotionEvent,
        val imageViewSize: Size,
    ) : TextSelectionEvent()
}
