package pl.kamilszustak.read.domain.usecase.barcode

import androidx.camera.core.ImageProxy
import com.google.android.gms.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withDefaultContext
import pl.kamilszustak.read.domain.access.usecase.barcode.ReadBarcodeUseCase
import javax.inject.Inject

class ReadBarcodeUseCaseImpl @Inject constructor() : ReadBarcodeUseCase {
    private val scannerOptions: BarcodeScannerOptions by lazy {
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.EAN_13)
            .build()
    }

    private val scanner: BarcodeScanner by lazy {
        BarcodeScanning.getClient(scannerOptions)
    }

    override suspend fun invoke(input: ImageProxy): Result<String> {
        val image = input.image
        if (image == null) {
            input.close()
            val exception = Exception("Image is not present in ImageProxy object")
            return Result.failure(exception)
        }

        val rotation = input.imageInfo.rotationDegrees
        val inputImage = InputImage.fromMediaImage(image, rotation)

        return withDefaultContext {
            runCatching {
                val barcodes = scanner.process(inputImage).await()
                input.close()
                if (barcodes.isNullOrEmpty()) {
                    throw Exception("Error occurred during image processing")
                } else {
                    barcodes.firstOrNull()?.displayValue ?: throw Exception("No barcodes detected")
                }
            }
        }
    }
}