package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.book.ArchiveBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveAllBooksUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val observeAllBooks: ObserveAllBooksUseCase,
    private val deleteBook: DeleteBookUseCase,
    private val editBook: EditBookUseCase,
    private val archiveBook: ArchiveBookUseCase
) : BaseViewModel<CollectionEvent, CollectionAction>() {

    val books: LiveData<List<Book>> = observeAllBooks()
        .map { books ->
            books.filter { !it.isArchived }
                .sortedByDescending(Book::modificationDate)
        }
        .onEach { books ->
            books.firstOrNull()?.let { book ->
                _currentBook.value = book
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    private val _currentBook: MutableLiveData<Book> = UniqueLiveData()
    val currentBook: LiveData<Book>
        get() = _currentBook

    val firstFastUpdateValue: Int = 5
    val secondFastUpdateValue: Int = 15
    val thirdFastUpdateValue: Int = 30

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

            is CollectionEvent.OnArchiveButtonClicked -> {
                _action.value = CollectionAction.NavigateToArchiveFragment
            }

            is CollectionEvent.OnReadingLogButtonClicked -> {
                _action.value = CollectionAction.NavigateToReadingLogFragment
            }

            is CollectionEvent.OnReadingGoalButtonClicked -> {
                _action.value = CollectionAction.NavigateToReadingGoalFragment
            }

            is CollectionEvent.OnFirstFastUpdateButtonClicked -> {
                handleFastUpdateButtonClick(firstFastUpdateValue)
            }

            is CollectionEvent.OnSecondFastUpdateButtonClicked -> {
                handleFastUpdateButtonClick(secondFastUpdateValue)
            }

            is CollectionEvent.OnThirdFastupdateButtonClicked -> {
                handleFastUpdateButtonClick(thirdFastUpdateValue)
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

            is CollectionEvent.OnArchiveBookButtonClicked -> {
                handleArchiveBookButtonClick(event)
            }

            is CollectionEvent.OnDeleteBookButtonClicked -> {
                handleDeleteBookButtonClick(event)
            }
        }
    }

    private fun handleArchiveBookButtonClick(event: CollectionEvent.OnArchiveBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            archiveBook(event.bookId)
                .onSuccess { _action.value = CollectionAction.BookArchived }
                .onFailure { _action.value = CollectionAction.Error(R.string.archiving_book_error_message) }
        }
    }

    private fun handleDeleteBookButtonClick(event: CollectionEvent.OnDeleteBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteBook(event.bookId)
                .onSuccess { _action.value = CollectionAction.BookDeleted }
                .onFailure { _action.value = CollectionAction.Error(R.string.deleting_book_error_message) }
        }
    }

    private fun handleFastUpdateButtonClick(value: Int) {
        val id = currentBook.value?.id ?: return
        viewModelScope.launch(Dispatchers.Main) {
            val result = editBook(id) { book ->
                book.copy(
                    readPages = book.readPages + value
                )
            }

            with(result) {
                onSuccess { _action.value = CollectionAction.ReadingProgressUpdated }
                onFailure { _action.value = CollectionAction.Error(R.string.reading_progress_edit_error_message) }
            }
        }
    }
}