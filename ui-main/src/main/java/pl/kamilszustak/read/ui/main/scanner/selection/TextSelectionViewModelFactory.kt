package pl.kamilszustak.read.ui.main.scanner.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class TextSelectionViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: TextSelectionFragmentArgs,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TextSelectionViewModel(
            arguments = arguments
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: TextSelectionFragmentArgs): TextSelectionViewModelFactory
    }
}