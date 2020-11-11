package pl.kamilszustak.read.ui.main.book.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.domain.access.usecase.book.DeleteBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.ObserveBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R

class BookDetailsViewModel(
    private val arguments: BookDetailsFragmentArgs,
    private val observeBook: ObserveBookUseCase,
    private val deleteBook: DeleteBookUseCase,
) : BaseViewModel<BookDetailsEvent, BookDetailsAction>() {

    private val bookId: BookId = BookId(arguments.bookId)
    val book: LiveData<Book> = observeBook(BookId(arguments.bookId))
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: BookDetailsEvent) {
        when (event) {
            BookDetailsEvent.OnEditBookButtonClicked -> {
                val bookId = BookId(arguments.bookId)
                _action.value = BookDetailsAction.NavigateToBookEditFragment(bookId)
            }

            BookDetailsEvent.OnDeleteBookButtonClicked -> {
                handleDeleteButtonClick()
            }

            BookDetailsEvent.OnUpdateReadingProgressButtonClicked, BookDetailsEvent.OnProgressBarClicked -> {
                _action.value = BookDetailsAction.NavigateToReadingProgressDialogFragment(bookId)
            }
        }
    }

    private fun handleDeleteButtonClick() {
        viewModelScope.launch(Dispatchers.Main) {
            deleteBook(bookId)
                .onSuccess {
                    with(_action) {
                        value = BookDetailsAction.BookDeleted
                        value = BookDetailsAction.NavigateUp
                    }
                }
                .onFailure {
                    _action.value = BookDetailsAction.Error(R.string.deleting_book_error_message)
                }
        }
    }
}