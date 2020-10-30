package pl.kamilszustak.read.ui.main.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.afollestad.assent.Permission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.resource.DrawableResource
import pl.kamilszustak.read.domain.access.usecase.barcode.ReadBarcodeUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.GetVolumeUseCase
import pl.kamilszustak.read.ui.base.util.PermissionState
import pl.kamilszustak.read.ui.base.util.getStateOf
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    private val getVolumeUseCase: GetVolumeUseCase,
    private val readBarcode: ReadBarcodeUseCase,
) : BaseViewModel<ScannerEvent, ScannerAction>() {

    private var permissionState: PermissionState = PermissionState.UNKNOWN
    private val barcodeDetected: AtomicBoolean = AtomicBoolean(false)

    private val isTorchEnabled: UniqueLiveData<Boolean> = UniqueLiveData(false)
    val torchButtonDrawable: LiveData<DrawableResource>
        get() = isTorchEnabled.map { isEnabled ->
            val resourceId = if (isEnabled) {
                R.drawable.icon_flash_on
            } else {
                R.drawable.icon_flash_off
            }

            DrawableResource(resourceId)
        }

    override fun handleEvent(event: ScannerEvent) {
        when (event) {
            ScannerEvent.OnResumed -> handleOnResumed()
            ScannerEvent.OnTorchButtonClicked -> handleFlashButtonClick()
            is ScannerEvent.OnCameraPermissionResult -> handleCameraPermissionResult(event)
            is ScannerEvent.OnImageCaptured -> handleOnImageCaptured(event)
        }
    }

    private fun handleOnResumed() {
        setPermissionState(permissionState)
    }

    private fun handleFlashButtonClick() {
        val state = !(isTorchEnabled.value ?: true)
        isTorchEnabled.value = state
        _action.value = ScannerAction.ChangeTorchState(state)
    }

    private fun setPermissionState(state: PermissionState) {
        _action.value = when (permissionState) {
            PermissionState.UNKNOWN -> ScannerAction.CameraPermissionAction.Unknown
            PermissionState.GRANTED -> ScannerAction.CameraPermissionAction.Granted
            PermissionState.DENIED -> ScannerAction.CameraPermissionAction.Denied
            PermissionState.PERMANENTLY_DENIED -> ScannerAction.CameraPermissionAction.PermanentlyDenied
        }
    }

    private fun handleCameraPermissionResult(event: ScannerEvent.OnCameraPermissionResult) {
        permissionState = event.result.getStateOf(Permission.CAMERA)
        setPermissionState(permissionState)
    }

    private fun handleOnImageCaptured(event: ScannerEvent.OnImageCaptured) {
        if (barcodeDetected.get()) {
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            barcodeDetected.set(true)
            readBarcode(event.imageProxy)
                .onSuccess { value ->
                    if (value != null) {
                        getVolumeUseCase(value)
                            .onSuccess { volume ->
                                if (volume != null) {
                                    _action.value = ScannerAction.NavigateToBookEditFragment(volume = volume)
                                } else {
                                    _action.value = ScannerAction.NavigateToBookEditFragment(isbn = value)
                                    barcodeDetected.set(false)
                                }
                            }
                            .onFailure {
                                setScanningError(it)
                            }
                    }
                }
                .onFailure {
                    event.imageProxy.close()
                    setScanningError(it)
                }

            _isLoading.value = false
            barcodeDetected.set(false)
        }
    }

    private fun setScanningError(throwable: Throwable) {
        _action.value = ScannerAction.Error(throwable)
        barcodeDetected.set(false)
    }
}