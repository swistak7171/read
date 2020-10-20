package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.EditCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase

class BookEditViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: BookEditFragmentArgs,
    private val getCollectionBookUseCase: GetCollectionBookUseCase,
    private val addCollectionBookUseCase: AddCollectionBookUseCase,
    private val editCollectionBookUseCase: EditCollectionBookUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BookEditViewModel(
            arguments = arguments,
            getCollectionBook = getCollectionBookUseCase,
            addCollectionBook = addCollectionBookUseCase,
            editCollectionBook = editCollectionBookUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: BookEditFragmentArgs): BookEditViewModelFactory
    }
}