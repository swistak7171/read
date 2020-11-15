package pl.kamilszustak.read.ui.main.scanner.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadTextUseCase

class TextSelectionViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: TextSelectionFragmentArgs,
    private val resourceProvider: ResourceProvider,
    private val readBitmapUseCase: ReadBitmapUseCase,
    private val readTextUseCase: ReadTextUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TextSelectionViewModel(
            arguments = arguments,
            resourceProvider = resourceProvider,
            readBitmap = readBitmapUseCase,
            readText = readTextUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: TextSelectionFragmentArgs): TextSelectionViewModelFactory
    }
}