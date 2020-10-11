package pl.kamilszustak.read.ui.main.scanner

import com.afollestad.assent.AssentResult
import pl.kamilszustak.read.ui.base.view.Event

sealed class ScannerEvent : Event {
    object OnResumed : ScannerEvent()
    data class OnCameraPermissionResult(
        val result: AssentResult,
    ) : ScannerEvent()
}