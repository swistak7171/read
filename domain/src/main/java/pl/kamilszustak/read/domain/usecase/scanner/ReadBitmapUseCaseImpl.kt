package pl.kamilszustak.read.domain.usecase.scanner

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.core.net.toFile
import pl.kamilszustak.read.common.util.withDefaultContext
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import java.io.File
import javax.inject.Inject

class ReadBitmapUseCaseImpl @Inject constructor() : ReadBitmapUseCase {
    override suspend fun invoke(file: File): Bitmap? {
        return withDefaultContext {
            val bitmap = decodeFile(file) ?: return@withDefaultContext null
            val degrees = checkRotationDegree(file)

            if (degrees != -1) {
                rotate(bitmap, degrees)
            } else {
                bitmap
            }
        }
    }

    override suspend fun invoke(uri: Uri): Bitmap? =
        invoke(uri.toFile())

    private suspend fun decodeFile(file: File): Bitmap? {
        val bytes = withIOContext {
            file.readBytes()
        }

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun checkRotationDegree(file: File): Int {
        val exif = ExifInterface(file.path)
        val orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION)
            ?.toIntOrNull()

        return when (orientation) {
            ExifInterface.ORIENTATION_UNDEFINED, ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> -1
        }
    }

    private fun rotate(bitmap: Bitmap, degrees: Int): Bitmap? {
        val matrix = Matrix()
        matrix.setRotate(degrees.toFloat())

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}