package pl.kamilszustak.read.ui.main.collection.archive

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveAllBooksUseCase
import pl.kamilszustak.read.domain.access.usecase.book.UnarchiveBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ArchiveViewModel @Inject constructor(
    private val observeAllBooksUseCase: ObserveAllBooksUseCase,
    private val unarchiveBook: UnarchiveBookUseCase,
    private val deleteBook: DeleteBookUseCase,
) : BaseViewModel<ArchiveEvent, ArchiveAction>() {

    val books: LiveData<List<Book>> = observeAllBooksUseCase()
        .map { books ->
            books.filter { it.isArchived }
                .sortedByDescending(Book::archivingDate)
        }
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: ArchiveEvent) {
        when (event) {
            is ArchiveEvent.OnUnarchiveBookButtonClicked -> {
                handleUnarchiveBookButtonClick(event)
            }

            is ArchiveEvent.OnDeleteBookDialogPositiveButtonClick -> {
                handleDeleteBookDialogPositiveButtonClick(event)
            }
        }
    }

    private fun handleUnarchiveBookButtonClick(event: ArchiveEvent.OnUnarchiveBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            unarchiveBook(event.id)
                .onSuccess { _action.value = ArchiveAction.ShowSuccessToast(R.string.book_unarchiving_success_message) }
                .onFailure { _action.value = ArchiveAction.ShowErrorToast(R.string.book_unarchiving_error_message) }
        }
    }

    private fun handleDeleteBookDialogPositiveButtonClick(event: ArchiveEvent.OnDeleteBookDialogPositiveButtonClick) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteBook(event.id)
                .onSuccess { _action.value = ArchiveAction.ShowSuccessToast(R.string.book_deleted) }
                .onFailure { _action.value = ArchiveAction.ShowErrorToast(R.string.deleting_book_error_message) }
        }
    }
}