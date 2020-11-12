package pl.kamilszustak.read.domain.usecase.scanner

import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.withDefaultContext
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadTextUseCase
import javax.inject.Inject

class ReadTextUseCaseImpl @Inject constructor() : ReadTextUseCase {
    private val recognizer: TextRecognizer by lazy {
        TextRecognition.getClient()
    }

    override suspend fun invoke(input: ImageProxy): Result<Text> {
        val image = input.image
        if (image == null) {
            val exception = Exception("Image is not not present in ImageProxy object")
            return Result.failure(exception)
        }

        val rotation = input.imageInfo.rotationDegrees
        val inputImage = InputImage.fromMediaImage(image, rotation)

        return withDefaultContext {
            runCatching {
                val text = recognizer.process(inputImage).await()
                text ?: throw Exception("Text not recognized")
            }
        }
    }
}