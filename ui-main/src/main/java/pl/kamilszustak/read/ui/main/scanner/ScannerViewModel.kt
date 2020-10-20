package pl.kamilszustak.read.ui.main.scanner

import androidx.lifecycle.viewModelScope
import com.afollestad.assent.Permission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.barcode.ReadBarcodeUseCase
import pl.kamilszustak.read.ui.base.util.PermissionState
import pl.kamilszustak.read.ui.base.util.getStateOf
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    private val readBarcode: ReadBarcodeUseCase,
) : BaseViewModel<ScannerEvent, ScannerAction>() {
    private val barcodeDetected: AtomicBoolean = AtomicBoolean(false)

    override fun handleEvent(event: ScannerEvent) {
        when (event) {
            ScannerEvent.OnResumed -> handleOnResumed()
            is ScannerEvent.OnCameraPermissionResult -> handleCameraPermissionResult(event)
            is ScannerEvent.OnImageCaptured -> handleOnImageCaptured(event)
        }
    }

    private fun handleOnResumed() {
        _action.value = ScannerAction.CameraPermissionAction.Unknown
    }

    private fun handleCameraPermissionResult(event: ScannerEvent.OnCameraPermissionResult) {
        val action = event.result.getStateOf(Permission.CAMERA)
        _action.value = when (action) {
            PermissionState.GRANTED -> ScannerAction.CameraPermissionAction.Granted
            PermissionState.DENIED -> ScannerAction.CameraPermissionAction.Denied
            PermissionState.PERMANENTLY_DENIED -> ScannerAction.CameraPermissionAction.PermanentlyDenied
            else -> null
        }
    }

    private fun handleOnImageCaptured(event: ScannerEvent.OnImageCaptured) {
        if (barcodeDetected.get()) {
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            readBarcode(event.imageProxy)
                .onSuccess { value ->
                    _action.value = ScannerAction.BarcodeDetected(value)
                }
                .onFailure { throwable ->
                    _action.value = ScannerAction.Error(throwable)
                }

            barcodeDetected.set(true)
        }
    }
}