package pl.kamilszustak.read.ui.main.scanner

import androidx.lifecycle.viewModelScope
import com.afollestad.assent.Permission
import com.google.android.gms.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.ui.base.util.PermissionState
import pl.kamilszustak.read.ui.base.util.getStateOf
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ScannerViewModel @Inject constructor() : BaseViewModel<ScannerEvent, ScannerState>() {
    private val scannerOptions: BarcodeScannerOptions by lazy {
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.EAN_13)
            .build()
    }

    private val scanner: BarcodeScanner by lazy {
        BarcodeScanning.getClient(scannerOptions)
    }

    private val barcodeDetected: AtomicBoolean = AtomicBoolean(false)

    override fun handleEvent(event: ScannerEvent) {
        when (event) {
            ScannerEvent.OnResumed -> handleOnResumed()
            is ScannerEvent.OnCameraPermissionResult -> handleCameraPermissionResult(event)
            is ScannerEvent.OnImageCaptured -> handleOnImageCaptured(event)
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

    private fun handleOnImageCaptured(event: ScannerEvent.OnImageCaptured) {
        if (barcodeDetected.get()) {
            return
        }

        val image = event.imageProxy.image
        if (image == null) {
            event.imageProxy.close()
            return
        }

        val rotation = event.imageProxy.imageInfo.rotationDegrees
        val inputImage = InputImage.fromMediaImage(image, rotation)

        viewModelScope.launch(Dispatchers.Default) {
            val barcodes = scanner.process(inputImage).await()
            event.imageProxy.close()
            if (!barcodes.isNullOrEmpty()) {
                val value = barcodes.firstOrNull()?.displayValue ?: return@launch
                barcodeDetected.set(true)
                val state = ScannerState.BarcodeDetected(value)
                _state.postValue(state)
            }
        }
    }
}