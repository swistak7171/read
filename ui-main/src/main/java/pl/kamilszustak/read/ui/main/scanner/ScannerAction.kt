package pl.kamilszustak.read.ui.main.scanner

import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class ScannerAction : ViewAction {
    sealed class CameraPermissionAction : ScannerAction() {
        object Unknown : CameraPermissionAction()
        object Granted : CameraPermissionAction()
        object Denied : CameraPermissionAction()
        object PermanentlyDenied : CameraPermissionAction()
    }

    data class Error(
        val throwable: Throwable,
    ) : ScannerAction()

    data class NavigateToBookEditFragment(
        val volume: Volume,
    ) : ScannerAction()
}