package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.withMainContext
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel

class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private val _imageBitmap: UniqueLiveData<Bitmap> = UniqueLiveData()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val file = arguments.imageUri.toFile()
            val bytes = file.readBytes()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bitmap != null) {
                withMainContext {
                    _imageBitmap.value = bitmap
                }
            }
        }
    }

    override fun handleEvent(event: TextSelectionEvent) {
    }
}