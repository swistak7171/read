package pl.kamilszustak.read.ui.main.book.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.EditCollectionBookUseCase

class ReadingProgressViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: ReadingProgressDialogFragmentArgs,
    private val getCollectionBookUseCase: GetCollectionBookUseCase,
    private val editCollectionBookUseCase: EditCollectionBookUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ReadingProgressViewModel(
            arguments = arguments,
            getCollectionBook = getCollectionBookUseCase,
            updateCollectionBook = editCollectionBookUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: ReadingProgressDialogFragmentArgs): ReadingProgressViewModelFactory
    }
}