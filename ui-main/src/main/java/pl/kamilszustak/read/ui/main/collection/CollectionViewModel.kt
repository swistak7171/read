package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
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
        .also { booksFlow ->
            viewModelScope.launch {
                val book = booksFlow.firstOrNull()?.firstOrNull()
                if (book != null) {
                    _currentBook.value = book
                }
            }
        }
        .map { it.sortedByDescending(Book::modificationDate) }
        .asLiveData(viewModelScope.coroutineContext)

    private val _currentBook: UniqueLiveData<Book> = UniqueLiveData()
    val currentBook: LiveData<Book> = _currentBook

    override fun handleEvent(event: CollectionEvent) {
        when (event) {
            CollectionEvent.OnAddBookButtonClicked -> {
                _action.value = CollectionAction.ShowAddBookDialog(R.array.add_book_dialog_options)
            }

            is CollectionEvent.OnScrolled -> {
                val book = books.value?.getOrNull(event.position)
                if (book != null) {
                    _currentBook.value = book
                }
            }

            is CollectionEvent.OnBookClicked -> {
                _action.value = CollectionAction.NavigateToBookDetailsFragment(event.bookId)
            }

            CollectionEvent.OnReadingLogButtonClicked -> {
                _action.value = CollectionAction.NavigateToReadingLogFragment
            }

            is CollectionEvent.OnDialogOptionSelected -> {
                when (event.index) {
                    0 -> _action.value = CollectionAction.NavigateToBookEditFragment()
                    1 -> _action.value = CollectionAction.NavigateToSearchFragment
                    2 -> _action.value = CollectionAction.NavigateToScannerFragment
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