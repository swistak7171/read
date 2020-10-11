package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.DateFormats
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.model.data.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import java.util.Date
import javax.inject.Inject

class BookEditViewModel @Inject constructor(
    private val dateFormats: DateFormats,
    private val addCollectionBook: AddCollectionBookUseCase,
) : BaseViewModel<BookEditEvent, BookEditState>() {

    private val _actionBarTitle: UniqueLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val bookTitle: UniqueLiveData<String> = UniqueLiveData()
    val bookAuthor: UniqueLiveData<String> = UniqueLiveData()
    val numberOfPages: UniqueLiveData<Int> = UniqueLiveData()
    val bookIsbn: UniqueLiveData<String?> = UniqueLiveData()
    val bookDescription: UniqueLiveData<String?> = UniqueLiveData()

    private val _bookPublicationDate: UniqueLiveData<Date?> = UniqueLiveData()
    val bookPublicationDate: LiveData<String?>
        get() = _bookPublicationDate.map { date ->
            date.useOrNull { dateFormats.dateFormat.format(it)  }
        }

    init {
        _actionBarTitle.value = R.string.add_book
    }

    override fun handleEvent(event: BookEditEvent) {
        when (event) {
            BookEditEvent.OnDateEditTextClicked -> {
                _state.value = BookEditState.OpenDatePicker
            }

            is BookEditEvent.OnPublicationDateSelected -> {
                _bookPublicationDate.value = event.date
            }

            BookEditEvent.OnAddBookButtonClicked -> {
                addBook()
            }
        }
    }

    private fun addBook() {
        val title = bookTitle.value
        val author = bookAuthor.value
        val pages = numberOfPages.value
        val isbn = bookIsbn.value
        val date = _bookPublicationDate.value
        val description = bookDescription.value

        if (title.isNullOrBlank()) {
            _state.value = BookEditState.Error(R.string.blank_book_title)
            return
        }

        if (author.isNullOrBlank()) {
            _state.value = BookEditState.Error(R.string.blank_book_author)
            return
        }

        if (pages == null || pages == 0) {
            _state.value = BookEditState.Error(R.string.blank_book_number_of_pages)
            return
        }

        val book = pl.kamilszustak.read.model.data.CollectionBook(
            title = title,
            author = author,
            numberOfPages = pages,
            publicationDate = date,
            isbn = isbn,
            description = description
        )

        viewModelScope.launch(Dispatchers.Main) {
            addCollectionBook(book)
                .onSuccess {
                    _state.value = BookEditState.BookAdded
                }
                .onFailure {
                    _state.value = BookEditState.Error(R.string.adding_book_error_message)
                }
        }
    }
}