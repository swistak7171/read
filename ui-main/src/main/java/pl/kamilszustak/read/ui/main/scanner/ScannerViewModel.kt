package pl.kamilszustak.read.ui.main.scanner

import com.afollestad.assent.Permission
import pl.kamilszustak.read.ui.base.util.PermissionState
import pl.kamilszustak.read.ui.base.util.getStateOf
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ScannerViewModel @Inject constructor() : BaseViewModel<ScannerEvent, ScannerState>() {
    override fun handleEvent(event: ScannerEvent) {
        when (event) {
            ScannerEvent.OnResumed -> handleOnResumed()
            is ScannerEvent.OnCameraPermissionResult -> handleCameraPermissionResult(event)
        }
    }

    private fun handleOnResumed() {
        _state.value = ScannerState.CameraPermissionState.Unknown
    }

    private fun handleCameraPermissionResult(event: ScannerEvent.OnCameraPermissionResult) {
        val state = event.result.getStateOf(Permission.CAMERA)
        _state.value = when (state) {
            PermissionState.GRANTED -> ScannerState.CameraPermissionState.Granted
            PermissionState.DENIED -> ScannerState.CameraPermissionState.Denied
            PermissionState.PERMANENTLY_DENIED -> ScannerState.CameraPermissionState.PermanentlyDenied
            else -> null
        }
    }
}