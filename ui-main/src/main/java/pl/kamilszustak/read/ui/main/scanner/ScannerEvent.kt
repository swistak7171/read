package pl.kamilszustak.read.ui.main.scanner

import android.net.Uri
import androidx.camera.core.ImageProxy
import com.afollestad.assent.AssentResult
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class ScannerEvent : ViewEvent {
    object OnResumed : ScannerEvent()
    object OnTorchButtonClicked : ScannerEvent()
    object OnScanButtonClicked : ScannerEvent()

    data class OnCameraPermissionResult(
        val result: AssentResult,
    ) : ScannerEvent()

    data class OnTabSelected(
        val index: Int,
    ) : ScannerEvent()

    data class OnSwiped(
        val direction: ScannerSwipeDirection,
    ) : ScannerEvent()

    data class OnImageCaptured(
        val imageProxy: ImageProxy,
    ) : ScannerEvent()

    data class OnImageSaved(
        val uri: Uri,
    ) : ScannerEvent()
}