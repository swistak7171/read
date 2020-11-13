package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.*
import android.graphics.Bitmap.createBitmap
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
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel

class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
    private val resourceProvider: ResourceProvider,
    private val readBitmap: ReadBitmapUseCase,
    private val readText: ReadTextUseCase,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private lateinit var originalBitmap: Bitmap
    private lateinit var drawBitmap: Bitmap

    private val _imageBitmap: MutableLiveData<Bitmap> = UniqueLiveData()
    val imageDrawable: LiveData<HighlightableBitmap> = _imageBitmap.map { bitmap ->
        HighlightableBitmap(resourceProvider.resources, bitmap, Color.parseColor("#88000000"))
    }

    private var selectionMode: TextSelectionMode = TextSelectionMode.default

    private val canvas: Canvas = Canvas()
    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 4F
        color = Color.RED
    }

    private var startPoint: PointF = PointF(0F, 0F)
    private var isMoving: Boolean = false

    init {
        viewModelScope.launch(Dispatchers.Main) {
            originalBitmap = readBitmap(arguments.imageUri) ?: return@launch
            drawRectangles()
        }
    }

    private fun drawRectangles() {
        drawBitmap = createBitmap(originalBitmap)

        canvas.setBitmap(drawBitmap)

//        arguments.text.blocks.forEach blocksForEach@{ block ->
//            block.components.forEach { line ->
//                when (selectionMode) {
//                    TextSelectionMode.BLOCKS -> {
//                        canvas.drawRect(block.boundingBox, paint)
//                        return@blocksForEach
//                    }
//
//                    TextSelectionMode.LINES -> {
//                        canvas.drawRect(line.boundingBox, paint)
//                    }
//
//                    TextSelectionMode.WORDS -> {
//                        line.components.forEach { element ->
//                            canvas.drawRect(element.boundingBox, paint)
//                        }
//                    }
//                }
//            }
//        }

        _imageBitmap.value = drawBitmap
    }

    override fun handleEvent(event: TextSelectionEvent) {
        when (event) {
            TextSelectionEvent.OnTextSelectionModeButtonClicked -> handleTextSelectionModeButtonClick()
            is TextSelectionEvent.OnTextSelectionModeSelected -> handleModeSelection(event)
            is TextSelectionEvent.OnImageViewTouch -> handleImageViewTouch(event)
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

    private fun handleImageViewTouch(event: TextSelectionEvent.OnImageViewTouch) {
        val widthRatio = originalBitmap.width / event.imageViewSize.width
        val heightRatio = originalBitmap.height / event.imageViewSize.height
        val point = PointF(
            event.motionEvent.x,
            event.motionEvent.y
        )

        when (event.motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                startPoint = point
                isMoving = true
            }

            MotionEvent.ACTION_MOVE -> {
                if (isMoving) {
                    val left = if (startPoint.x < point.x) startPoint.x else point.x
                    val top = if (startPoint.y < point.y) startPoint.y else point.y
                    val right = if (startPoint.x < point.x) point.x else startPoint.x
                    val bottom = if (startPoint.y < point.y) point.y else startPoint.y
                    val rectangle = RectF(
                        left * widthRatio,
                        top * heightRatio,
                        right * widthRatio,
                        bottom * heightRatio
                    )

//                    imageBitmap.value?.clearSelection()
                    imageDrawable.value?.selectArea(rectangle)
                    _action.value = TextSelectionAction.InvalidateImageView
                }
            }

            MotionEvent.ACTION_UP -> {
                isMoving = false
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
                isMoving = false
            }
        }
    }

    private fun readTextFromBitmap() {
        viewModelScope.launch(Dispatchers.Main) {
            readText(originalBitmap)
                .onSuccess { text ->

                }.onFailure { throwable ->
                    _action.value = TextSelectionAction.Error(throwable = throwable)
                }
        }
    }
}