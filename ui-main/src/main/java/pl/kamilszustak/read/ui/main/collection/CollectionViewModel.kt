package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveAllBooksUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val observeAllBooks: ObserveAllBooksUseCase,
    private val deleteBook: DeleteBookUseCase,
) : BaseViewModel<CollectionEvent, CollectionAction>() {

    val books: LiveData<List<Book>> = observeAllBooks()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: CollectionEvent) {
        when (event) {
            CollectionEvent.OnAddBookButtonClicked -> {
                _action.value = CollectionAction.ShowAddBookDialog(R.array.add_book_dialog_options)
            }

            is CollectionEvent.OnDialogOptionSelected -> {
                when (event.index) {
                    0 -> _action.value = CollectionAction.NavigateToBookEditFragment()
                    1 -> _action.value = CollectionAction.NavigateToSearchFragment
                }
            }

            is CollectionEvent.OnBookLongClicked -> {
                _action.value = CollectionAction.NavigateToReadingProgressDialogFragment(event.bookId)
            }

            is CollectionEvent.OnUpdateReadingProgressButtonClicked -> {
                _action.value = CollectionAction.NavigateToReadingProgressDialogFragment(event.bookId)
            }

            is CollectionEvent.OnEditBookButtonClicked -> {
                _action.value = CollectionAction.NavigateToBookEditFragment(event.bookId)
            }

            is CollectionEvent.OnDeleteBookButtonClicked -> {
                handleDeleteBookButtonClick(event)
            }
        }
    }

    private fun handleDeleteBookButtonClick(event: CollectionEvent.OnDeleteBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteBook(event.bookId)
                .onSuccess { _action.value = CollectionAction.BookDeleted }
                .onFailure { _action.value = CollectionAction.Error(R.string.deleting_book_error_message) }
        }
    }
}