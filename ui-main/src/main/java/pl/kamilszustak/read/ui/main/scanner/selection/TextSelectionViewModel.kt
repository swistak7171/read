package pl.kamilszustak.read.ui.main.scanner.selection

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel


class TextSelectionViewModel(
    private val arguments: TextSelectionFragmentArgs,
    private val readBitmap: ReadBitmapUseCase,
) : BaseViewModel<TextSelectionEvent, TextSelectionAction>() {

    private val _imageBitmap: UniqueLiveData<Bitmap> = UniqueLiveData()
    val imageBitmap: LiveData<Bitmap>
        get() = _imageBitmap

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val bitmap = readBitmap(arguments.imageUri) ?: return@launch
            _imageBitmap.value = bitmap
        }
    }

    override fun handleEvent(event: TextSelectionEvent) {
    }
}