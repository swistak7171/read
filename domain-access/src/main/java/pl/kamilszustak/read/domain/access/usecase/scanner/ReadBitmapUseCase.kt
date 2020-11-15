package pl.kamilszustak.read.domain.access.usecase.scanner

import android.graphics.Bitmap
import android.net.Uri
import pl.kamilszustak.read.domain.access.usecase.BaseUseCase
import java.io.File

interface ReadBitmapUseCase : BaseUseCase {
    suspend operator fun invoke(file: File): Bitmap?
    suspend operator fun invoke(uri: Uri): Bitmap?
}