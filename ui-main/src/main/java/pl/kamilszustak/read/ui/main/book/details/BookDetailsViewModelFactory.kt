package pl.kamilszustak.read.ui.main.book.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveBookUseCase

class BookDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: BookDetailsFragmentArgs,
    private val observeBookUseCase: ObserveBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BookDetailsViewModel(
            arguments = arguments,
            observeBook = observeBookUseCase,
            deleteBook = deleteBookUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: BookDetailsFragmentArgs): BookDetailsViewModelFactory
    }
}