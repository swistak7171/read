package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.common.date.DateFormats
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.usecase.book.AddBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.model.domain.IsbnType
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import java.util.*

class BookEditViewModel(
    private val arguments: BookEditFragmentArgs,
    private val getBook: GetBookUseCase,
    private val addBook: AddBookUseCase,
    private val editBook: EditBookUseCase,
) : BaseViewModel<BookEditEvent, BookEditAction>() {

    private val inEditMode: Boolean = (arguments.bookId != null)

    private val _actionBarTitle: MutableLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val bookTitle: MutableLiveData<String> = UniqueLiveData()
    val bookAuthor: MutableLiveData<String> = UniqueLiveData()
    val bookPagesNumber: MutableLiveData<Int> = UniqueLiveData()
    val bookReadPages: MutableLiveData<Int> = UniqueLiveData(0)
    val bookIsbn: MutableLiveData<String?> = UniqueLiveData()
    val bookDescription: MutableLiveData<String?> = UniqueLiveData()
    private var coverImageUrl: String? = null

    private val _bookPublicationDate: MutableLiveData<Date?> = UniqueLiveData()
    val bookPublicationDate: LiveData<String?>
        get() = _bookPublicationDate.map { date ->
            date.useOrNull { DateFormats.dateFormat.format(it)  }
        }

    init {
        _actionBarTitle.value = if (inEditMode) {
            R.string.edit_book
        } else {
            R.string.add_book
        }

        when {
            inEditMode -> {
                viewModelScope.launch(Dispatchers.Main) {
                    val id = BookId(arguments.bookId ?: return@launch)
                    val book = getBook(id)
                    if (book != null) {
                        assignBookDetails(book)
                    }
                }
            }

            arguments.volume != null -> {
                assignVolumeDetails(arguments.volume)
            }

            arguments.isbn != null -> {
                bookIsbn.value = arguments.isbn
            }
        }
    }

    override fun handleEvent(event: BookEditEvent) {
        when (event) {
            BookEditEvent.OnDateEditTextClicked -> {
                val date = _bookPublicationDate.value ?: Date()
                _action.value = BookEditAction.OpenDatePicker(date)
            }

            BookEditEvent.OnDateClearButtonClicked -> {
                _bookPublicationDate.value = null
            }

            is BookEditEvent.OnPublicationDateSelected -> {
                _bookPublicationDate.value = event.date
            }

            BookEditEvent.OnSaveBookButtonClicked -> {
                handleSaveButtonClick()
            }
        }
    }

    private fun assignVolumeDetails(volume: Volume) {
        var isbn = volume.isbns?.find { it.type == IsbnType.ISBN_13 }
        if (isbn == null) {
            isbn = volume.isbns?.firstOrNull()
        }

        bookTitle.value = volume.title
        bookAuthor.value = volume.author
        bookPagesNumber.value = volume.pagesNumber
        bookIsbn.value = isbn?.value
        _bookPublicationDate.value = volume.publicationDate
        bookDescription.value = volume.description
        coverImageUrl = volume.coverImageUrl
    }

    private fun assignBookDetails(book: Book) {
        bookTitle.value = book.title
        bookAuthor.value = book.author
        bookPagesNumber.value = book.pagesNumber
        bookReadPages.value = book.readPages
        bookIsbn.value = book.isbn
        _bookPublicationDate.value = book.publicationDate
        bookDescription.value = book.description
    }

    private fun handleSaveButtonClick() {
        val title = bookTitle.value
        val author = bookAuthor.value
        val pages = bookPagesNumber.value
        val readPages = bookReadPages.value ?: 0
        val isbn = bookIsbn.value
        val date = _bookPublicationDate.value
        val description = bookDescription.value

        if (title.isNullOrBlank()) {
            _action.value = BookEditAction.Error(R.string.blank_book_title)
            return
        }

        if (author.isNullOrBlank()) {
            _action.value = BookEditAction.Error(R.string.blank_book_author)
            return
        }

        if (pages == null || pages == 0) {
            _action.value = BookEditAction.Error(R.string.blank_book_number_of_pages)
            return
        }

        if (readPages > pages) {
            _action.value = BookEditAction.Error(R.string.read_pages_over_number_of_pages)
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            val result = if (inEditMode) {
                val id = BookId(arguments.bookId ?: return@launch)
                editBook(id) { book ->
                    book.copy(
                        title = title,
                        author = author,
                        pagesNumber = pages,
                        readPages = readPages,
                        publicationDate = date,
                        isbn = isbn,
                        description = description
                    )
                }
            } else {
                val book = Book(
                    volumeId = arguments.volume?.id,
                    title = title,
                    author = author,
                    pagesNumber = pages,
                    readPages = readPages,
                    publicationDate = date,
                    isbn = isbn,
                    description = description,
                    coverImageUrl = coverImageUrl
                )

                addBook(book)
            }

            result.onSuccess {
                val resourceId = if (inEditMode) {
                    R.string.book_edited_successfully
                } else {
                    R.string.book_added_successfully
                }

                with(_action) {
                    value = BookEditAction.BookSaved(resourceId)
                    value = BookEditAction.NavigateUp
                }
            }.onFailure {
                val resourceId = if (inEditMode) {
                    R.string.editing_book_error_message
                } else {
                    R.string.adding_book_error_message
                }

                _action.value = BookEditAction.Error(resourceId)
            }
        }
    }
}