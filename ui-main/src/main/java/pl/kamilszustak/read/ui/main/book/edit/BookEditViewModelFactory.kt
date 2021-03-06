package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.book.AddBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase

class BookEditViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: BookEditFragmentArgs,
    private val getBookUseCase: GetBookUseCase,
    private val addBookUseCase: AddBookUseCase,
    private val editBookUseCase: EditBookUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BookEditViewModel(
            arguments = arguments,
            getBook = getBookUseCase,
            addBook = addBookUseCase,
            editBook = editBookUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: BookEditFragmentArgs): BookEditViewModelFactory
    }
}