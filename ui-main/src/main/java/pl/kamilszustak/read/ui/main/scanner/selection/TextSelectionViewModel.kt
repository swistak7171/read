package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.withMainContext
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import java.io.File


class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private val _imageBitmap: UniqueLiveData<Bitmap> = UniqueLiveData()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val file = arguments.imageUri.toFile()
            val bitmap = decodeFile(file) ?: return@launch
            val degree = checkRotationDegree(file)
            val rotatedBitmap = if (degree != -1) {
                rotate(bitmap, degree)
            } else {
                bitmap
            }

            withMainContext {
                _imageBitmap.value = rotatedBitmap
            }
        }
    }

    private fun decodeFile(file: File): Bitmap? {
        val bytes = file.readBytes()

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun checkRotationDegree(file: File): Int {
        val exif = ExifInterface(file.path)
        val orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION)

        return when (orientation) {
            "6" -> 90
            "8" -> 270
            "3" -> 180
            "0" -> 90
            else -> -1
        }
    }

    private fun rotate(bitmap: Bitmap, degree: Int): Bitmap? {
        val matrix = Matrix()
        matrix.setRotate(degree.toFloat())

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

    override fun handleEvent(event: TextSelectionEvent) {
    }
}