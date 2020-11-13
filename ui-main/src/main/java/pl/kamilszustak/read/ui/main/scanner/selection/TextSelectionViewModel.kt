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

    private lateinit var originalBitmap: Bitmap
    private val _imageBitmap: MutableLiveData<Bitmap> = MutableLiveData()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    private var selectionMode: TextSelectionMode = TextSelectionMode.LINES

    init {
        viewModelScope.launch(Dispatchers.Main) {
            originalBitmap = readBitmap(arguments.imageUri) ?: return@launch
            drawRectangles()
        }
    }

    private fun drawRectangles() {
        val bitmap = Bitmap.createBitmap(originalBitmap)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 4F
            color = Color.RED
        }

        arguments.text.blocks.forEach blocksForEach@{ block ->
            block.components.forEach { line ->
                when (selectionMode) {
                    TextSelectionMode.BLOCKS -> {
                        canvas.drawRect(block.boundingBox, paint)
                        return@blocksForEach
                    }

                    TextSelectionMode.LINES -> {
                        canvas.drawRect(line.boundingBox, paint)
                    }

                    TextSelectionMode.WORDS -> {
                        line.components.forEach { element ->
                            canvas.drawRect(element.boundingBox, paint)
                        }
                    }
                }
            }
        }

        _imageBitmap.value = bitmap
    }

    override fun handleEvent(event: TextSelectionEvent) {
        when (event) {
            TextSelectionEvent.OnTextSelectionModeButtonClicked -> handleTextSelectionModeButtonClick()
            is TextSelectionEvent.OnTextSelectionModeSelected -> handleModeSelection(event)
        }
    }

    private fun handleTextSelectionModeButtonClick() {
        val selection = selectionMode.ordinal
        val items = TextSelectionMode.values()
            .map(TextSelectionMode::nameResourceId)

        _action.value = TextSelectionAction.ShowTextSelectionModeDialog(
            itemsResources = items,
            initialSelection = selection
        )
    }

    private fun handleModeSelection(event: TextSelectionEvent.OnTextSelectionModeSelected) {
        val mode = TextSelectionMode.values()
            .getOrNull(event.selectionIndex)

        if (mode != null) {
            selectionMode = mode
            drawRectangles()
        }
    }
}