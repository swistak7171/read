package pl.kamilszustak.read.ui.main.scanner

import androidx.camera.core.ImageProxy
import com.afollestad.assent.AssentResult
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ScannerEvent : ViewEvent {
    object OnResumed : ScannerEvent()

    data class OnCameraPermissionResult(
        val result: AssentResult,
    ) : ScannerEvent()

    data class OnImageCaptured(
        val imageProxy: ImageProxy,
    ) : ScannerEvent()
}