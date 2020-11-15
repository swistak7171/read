package pl.kamilszustak.read.domain.usecase.scanner

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withDefaultContext
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadTextUseCase
import pl.kamilszustak.read.model.domain.text.TextWrapper
import pl.kamilszustak.read.model.mapper.text.TextMapper
import javax.inject.Inject

class ReadTextUseCaseImpl @Inject constructor(
    private val application: Application,
    private val textMapper: TextMapper,
) : ReadTextUseCase {

    private val recognizer: TextRecognizer by lazy {
        TextRecognition.getClient()
    }

    override suspend fun invoke(imageProxy: ImageProxy): Result<TextWrapper> {
        val image = imageProxy.image
        if (image == null) {
            val exception = Exception("Image is not not present in ImageProxy object")
            return Result.failure(exception)
        }

        val rotation = imageProxy.imageInfo.rotationDegrees
        val inputImage = InputImage.fromMediaImage(image, rotation)

        return runProcessing(inputImage)
    }

    override suspend fun invoke(uri: Uri): Result<TextWrapper> {
        val inputImage = InputImage.fromFilePath(application, uri)

        return runProcessing(inputImage)
    }

    override suspend fun invoke(bitmap: Bitmap): Result<TextWrapper> {
        val inputImage = InputImage.fromBitmap(bitmap, 0)

        return runProcessing(inputImage)
    }

    private suspend fun runProcessing(inputImage: InputImage): Result<TextWrapper> {
        return withDefaultContext {
            runCatching {
                val text = recognizer.process(inputImage).await()

                if (text != null) {
                    textMapper.map(text)
                } else {
                    throw Exception("Text not recognized")
                }
            }
        }
    }
}