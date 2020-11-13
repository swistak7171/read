package pl.kamilszustak.read.ui.main.scanner.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase

class TextSelectionViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: TextSelectionFragmentArgs,
    private val readBitmapUseCase: ReadBitmapUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TextSelectionViewModel(
            arguments = arguments,
            readBitmap = readBitmapUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: TextSelectionFragmentArgs): TextSelectionViewModelFactory
    }
}