package pl.kamilszustak.read.ui.main.scanner

import androidx.camera.core.ImageProxy
import com.afollestad.assent.AssentResult
import pl.kamilszustak.read.ui.base.view.Event

sealed class ScannerEvent : Event {
    object OnResumed : ScannerEvent()

    data class OnCameraPermissionResult(
        val result: AssentResult,
    ) : ScannerEvent()

    data class OnImageCaptured(
        val imageProxy: ImageProxy,
    ) : ScannerEvent()
}