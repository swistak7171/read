package pl.kamilszustak.read.ui.main.scanner

import android.net.Uri
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.model.domain.text.TextWrapper
import pl.kamilszustak.read.ui.base.view.ViewAction
import java.io.File

sealed class ScannerAction : ViewAction {
    sealed class CameraPermissionAction : ScannerAction() {
        object Unknown : CameraPermissionAction()
        object Granted : CameraPermissionAction()
        object Denied : CameraPermissionAction()
        object PermanentlyDenied : CameraPermissionAction()
    }

    data class ChangeTorchState(
        val isEnabled: Boolean,
    ) : ScannerAction()

    data class TakePicture(
        val file: File?,
    ) : ScannerAction()

    data class Error(
        val messageResourceId: Int? = null,
        val throwable: Throwable? = null,
    ) : ScannerAction()

    data class NavigateToBookEditFragment(
        val volume: Volume? = null,
        val isbn: String? = null,
    ) : ScannerAction()

    data class NavigateToTextSelectionFragment(
        val text: TextWrapper,
        val imageUri: Uri,
    ) : ScannerAction()
}