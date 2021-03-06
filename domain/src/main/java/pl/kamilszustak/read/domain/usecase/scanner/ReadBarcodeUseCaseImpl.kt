package pl.kamilszustak.read.domain.usecase.scanner

import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withDefaultContext
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBarcodeUseCase
import javax.inject.Inject

class ReadBarcodeUseCaseImpl @Inject constructor() : ReadBarcodeUseCase {
    private val scanner: BarcodeScanner by lazy {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.TYPE_ISBN, Barcode.FORMAT_EAN_13)
            .build()

        BarcodeScanning.getClient(options)
    }

    override suspend fun invoke(input: ImageProxy): Result<String?> {
        val image = input.image
        if (image == null) {
            val exception = Exception("Image is not present in ImageProxy object")
            return Result.failure(exception)
        }

        val rotation = input.imageInfo.rotationDegrees
        val inputImage = InputImage.fromMediaImage(image, rotation)

        return withDefaultContext {
            runCatching {
                val barcodes = scanner.process(inputImage).await()
                barcodes.firstOrNull()?.displayValue
            }
        }
    }
}