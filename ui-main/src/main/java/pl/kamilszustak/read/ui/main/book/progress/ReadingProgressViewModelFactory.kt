package pl.kamilszustak.read.ui.main.book.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase

class ReadingProgressViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: ReadingProgressDialogFragmentArgs,
    private val getBookUseCase: GetBookUseCase,
    private val editBookUseCase: EditBookUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ReadingProgressViewModel(
            arguments = arguments,
            getBook = getBookUseCase,
            editBook = editBookUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: ReadingProgressDialogFragmentArgs): ReadingProgressViewModelFactory
    }
}