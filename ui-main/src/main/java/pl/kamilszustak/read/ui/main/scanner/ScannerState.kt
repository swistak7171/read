package pl.kamilszustak.read.ui.main.scanner

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.State

sealed class ScannerState : State {
    sealed class CameraPermissionState : ScannerState() {
        object Unknown : CameraPermissionState()
        object Granted : CameraPermissionState()
        object Denied : CameraPermissionState()
        object PermanentlyDenied : CameraPermissionState()
    }

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : ScannerState()

    data class BarcodeDetected(
        val value: String,
    ) : ScannerState()
}