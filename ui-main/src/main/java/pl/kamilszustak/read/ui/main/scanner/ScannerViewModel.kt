package pl.kamilszustak.read.ui.main.scanner

import android.app.Application
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.afollestad.assent.Permission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.resource.DrawableResource
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBarcodeUseCase
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.GetVolumeUseCase
import pl.kamilszustak.read.ui.base.util.PermissionState
import pl.kamilszustak.read.ui.base.util.getStateOf
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    private val application: Application,
    private val getVolumeUseCase: GetVolumeUseCase,
    private val readBarcode: ReadBarcodeUseCase,
    private val readBitmap: ReadBitmapUseCase,
) : BaseViewModel<ScannerEvent, ScannerAction>() {

    private var permissionState: PermissionState = PermissionState.UNKNOWN
    private val detected: AtomicBoolean = AtomicBoolean(false)

    private val _selectedMode: MutableLiveData<ScannerMode> = UniqueLiveData(ScannerMode.ISBN)
    val selectedMode: LiveData<ScannerMode>
        get() = _selectedMode

    private val isTorchEnabled: MutableLiveData<Boolean> = UniqueLiveData(false)
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
            is ScannerEvent.OnTabSelected -> handleTabSelection(event)
            is ScannerEvent.OnSwiped -> handleSwipe(event)
            is ScannerEvent.OnScanButtonClicked -> handleScanButtonClick()
            is ScannerEvent.OnImageCaptured -> handleOnImageCaptured(event)
            is ScannerEvent.OnImageSaved -> handleImageSave(event)
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

    private fun handleTabSelection(event: ScannerEvent.OnTabSelected) {
        _selectedMode.value = ScannerMode.values().getOrNull(event.index) ?: ScannerMode.default
    }

    private fun handleSwipe(event: ScannerEvent.OnSwiped) {
        _selectedMode.value = when (event.direction) {
            ScannerSwipeDirection.LEFT ->  ScannerMode.QUOTE
            ScannerSwipeDirection.RIGHT -> ScannerMode.ISBN
        }
    }

    private fun handleScanButtonClick() {
        val mode = _selectedMode.value ?: return
        val file = if (mode == ScannerMode.QUOTE) {
            val filename = System.currentTimeMillis().toString()
            File.createTempFile(filename, ".jpg", application.cacheDir)
        } else {
            null
        }

        _action.value = ScannerAction.TakePicture(file)
    }

    private fun handleOnImageCaptured(event: ScannerEvent.OnImageCaptured) {
        if (detected.get()) {
            return
        }

        val mode = _selectedMode.value
        if (mode != ScannerMode.ISBN) {
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            detected.set(true)
            performBarcodeRecognition(event.imageProxy)
            _isLoading.value = false
            detected.set(false)
        }
    }

    private suspend fun performBarcodeRecognition(imageProxy: ImageProxy) {
        readBarcode(imageProxy)
            .onSuccess { value ->
                if (value != null) {
                    getVolumeUseCase(value)
                        .onSuccess { volume ->
                            if (volume != null) {
                                _action.value = ScannerAction.NavigateToBookEditFragment(volume = volume)
                            } else {
                                _action.value = ScannerAction.NavigateToBookEditFragment(isbn = value)
                                detected.set(false)
                            }
                        }
                        .onFailure {
                            setError(it)
                        }
                }
            }
            .onFailure {
                imageProxy.close()
                setError(it)
            }
    }

    private fun handleImageSave(event: ScannerEvent.OnImageSaved) {
        val action = ScannerAction.NavigateToTextSelectionFragment(event.uri)
        _action.postValue(action)
    }

    private fun setError(throwable: Throwable) {
        _action.value = ScannerAction.Error(throwable = throwable)
        detected.set(false)
    }
}