package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.*
import android.util.Size
import android.view.MotionEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadTextUseCase
import pl.kamilszustak.read.model.domain.text.TextWrapper
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R

class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
    private val resourceProvider: ResourceProvider,
    private val readBitmap: ReadBitmapUseCase,
    private val readText: ReadTextUseCase,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private val _imageBitmap: MutableLiveData<Bitmap> = UniqueLiveData()
    val imageDrawable: LiveData<HighlightableBitmap> = _imageBitmap.map { bitmap ->
        HighlightableBitmap(
            resources = resourceProvider.resources,
            bitmap = bitmap,
            darkenColor = resourceProvider.getColor(R.color.text_selection_outside_area_color)
        )
    }

    private lateinit var originalBitmap: Bitmap
    private lateinit var drawBitmap: Bitmap

    private var selectionMode: TextSelectionMode = TextSelectionMode.default
    private var startPoint: PointF = PointF(0F, 0F)
    private var moveCounter: Int = 0
    private val MOVE_THRESHOLD: Int = 5

    private val canvas: Canvas = Canvas()
    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 4F
        color = Color.RED
    }

    init {
        viewModelScope.launch(Dispatchers.Main) {
            originalBitmap = readBitmap(arguments.imageUri) ?: return@launch
            drawBitmap = Bitmap.createBitmap(originalBitmap)
            _imageBitmap.value = drawBitmap
        }
    }

    override fun handleEvent(event: TextSelectionEvent) {
        when (event) {
            TextSelectionEvent.OnTextRecognitionButtonClicked -> handleTextRecognitionButtonClick()
            TextSelectionEvent.OnTextSelectionModeButtonClicked -> handleTextSelectionModeButtonClick()
            TextSelectionEvent.OnRestoreImageButtonClicked -> handleRestoreImageButtonClick()
            is TextSelectionEvent.OnTextSelectionModeSelected -> handleModeSelection(event)
            is TextSelectionEvent.OnImageViewTouch -> handleImageViewTouch(event)
        }
    }

    private fun handleTextRecognitionButtonClick() {
        val bitmap = imageDrawable.value?.getSelectedBitmap() ?: return
        viewModelScope.launch(Dispatchers.Main) {
            readText(bitmap)
                .onSuccess { text ->
                    _action.value = TextSelectionAction.NavigateToQuoteEditFragment(text.value)
                }.onFailure { throwable ->
                    _action.value = TextSelectionAction.Error(throwable = throwable)
                }
        }
    }

    private fun drawRectangles(bitmap: Bitmap, text: TextWrapper) {
        canvas.setBitmap(bitmap)
        text.blocks.forEach blocksForEach@{ block ->
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

        _action.value = TextSelectionAction.InvalidateImageView
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
        }
    }

    private fun handleRestoreImageButtonClick() {
        _imageBitmap.value = originalBitmap
        imageDrawable.value?.clearSelection()
    }

    private fun handleImageViewTouch(event: TextSelectionEvent.OnImageViewTouch) {
        val point = PointF(
            event.motionEvent.x,
            event.motionEvent.y
        )

        when (event.motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                moveCounter = 0
                startPoint = point
            }

            MotionEvent.ACTION_MOVE -> {
                moveCounter++
                val rectangle = createRectangle(point, event.imageViewSize)
                imageDrawable.value?.selectArea(rectangle)
            }

            MotionEvent.ACTION_UP -> {
                if (moveCounter < MOVE_THRESHOLD) {
                    imageDrawable.value?.clearSelection()
                }
            }
        }
    }

    private fun createRectangle(point: PointF, imageViewSize: Size): RectF {
        val widthRatio = originalBitmap.width / imageViewSize.width
        val heightRatio = originalBitmap.height / imageViewSize.height

        val left = if (startPoint.x < point.x) startPoint.x else point.x
        val top = if (startPoint.y < point.y) startPoint.y else point.y
        val right = if (startPoint.x < point.x) point.x else startPoint.x
        val bottom = if (startPoint.y < point.y) point.y else startPoint.y

        return RectF(
            left * widthRatio,
            top * heightRatio,
            right * widthRatio,
            bottom * heightRatio
        )
    }
}