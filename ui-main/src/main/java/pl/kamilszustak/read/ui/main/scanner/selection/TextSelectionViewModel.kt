package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel


class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
    private val readBitmap: ReadBitmapUseCase,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private val _imageBitmap: MutableLiveData<Bitmap> = MutableLiveData()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val bitmap = readBitmap(arguments.imageUri) ?: return@launch
            drawBoxes(bitmap)
        }
    }

    private fun drawBoxes(bitmap: Bitmap) {
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 4F
            color = Color.RED
        }

        arguments.text.blocks.forEach { block ->
            block.components.forEach { line ->
                canvas.drawRect(line.boundingBox, paint)
            }
        }

        _imageBitmap.value = bitmap
    }

    override fun handleEvent(event: TextSelectionEvent) {
    }
}