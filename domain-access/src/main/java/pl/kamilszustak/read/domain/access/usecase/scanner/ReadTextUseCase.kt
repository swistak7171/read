package pl.kamilszustak.read.domain.access.usecase.scanner

import android.graphics.Bitmap
import android.net.Uri
import androidx.camera.core.ImageProxy
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import pl.kamilszustak.read.model.domain.text.TextWrapper

interface ReadTextUseCase : BaseUseCase {
    suspend operator fun invoke(imageProxy: ImageProxy): Result<TextWrapper>
    suspend operator fun invoke(uri: Uri): Result<TextWrapper>
    suspend operator fun invoke(bitmap: Bitmap): Result<TextWrapper>
}