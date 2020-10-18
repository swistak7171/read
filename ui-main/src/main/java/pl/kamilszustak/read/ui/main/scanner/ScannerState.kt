package pl.kamilszustak.read.ui.main.scanner

import pl.kamilszustak.read.ui.base.view.ViewState

sealed class ScannerState : ViewState {
    sealed class CameraPermissionState : ScannerState() {
        object Unknown : CameraPermissionState()
        object Granted : CameraPermissionState()
        object Denied : CameraPermissionState()
        object PermanentlyDenied : CameraPermissionState()
    }

    data class Error(
        val throwable: Throwable,
    ) : ScannerState()

    data class BarcodeDetected(
        val value: String,
    ) : ScannerState()
}