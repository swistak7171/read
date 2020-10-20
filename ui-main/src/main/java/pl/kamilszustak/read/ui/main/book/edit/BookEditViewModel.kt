package pl.kamilszustak.read.ui.main.book.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.DateFormats
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.EditCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import java.util.*

class BookEditViewModel(
    private val arguments: BookEditFragmentArgs,
    private val dateFormats: DateFormats,
    private val getCollectionBook: GetCollectionBookUseCase,
    private val addCollectionBook: AddCollectionBookUseCase,
    private val editCollectionBook: EditCollectionBookUseCase,
) : BaseViewModel<BookEditEvent, BookEditAction>() {

    private val inEditMode: Boolean = (arguments.collectionBookId != null)

    private val _actionBarTitle: UniqueLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val bookTitle: UniqueLiveData<String> = UniqueLiveData()
    val bookAuthor: UniqueLiveData<String> = UniqueLiveData()
    val numberOfBookPages: UniqueLiveData<Int> = UniqueLiveData()
    val bookReadPages: UniqueLiveData<Int> = UniqueLiveData(0)
    val bookIsbn: UniqueLiveData<String?> = UniqueLiveData()
    val bookDescription: UniqueLiveData<String?> = UniqueLiveData()

    private val _bookPublicationDate: UniqueLiveData<Date?> = UniqueLiveData()
    val bookPublicationDate: LiveData<String?>
        get() = _bookPublicationDate.map { date ->
            date.useOrNull { dateFormats.dateFormat.format(it)  }
        }

    init {
        _actionBarTitle.value = if (inEditMode) {
            R.string.edit_book
        } else {
            R.string.add_book
        }

        if (inEditMode) {
            viewModelScope.launch(Dispatchers.Main) {
                val id = CollectionBookId(arguments.collectionBookId ?: return@launch)
                val book = getCollectionBook(id)
                if (book != null) {
                    assignBookDetails(book)
                }
            }
        }
    }

    override fun handleEvent(event: BookEditEvent) {
        when (event) {
            BookEditEvent.OnDateEditTextClicked -> {
                _action.value = BookEditAction.OpenDatePicker
            }

            is BookEditEvent.OnPublicationDateSelected -> {
                _bookPublicationDate.value = event.date
            }

            BookEditEvent.OnSaveBookButtonClicked -> {
                handleSaveButtonClick()
            }
        }
    }

    private fun assignBookDetails(book: CollectionBook) {
        bookTitle.value = book.title
        bookAuthor.value = book.author
        numberOfBookPages.value = book.pagesNumber
        bookReadPages.value = book.readPages
        bookIsbn.value = book.isbn
        _bookPublicationDate.value = book.publicationDate
        bookDescription.value = book.description
    }

    private fun handleSaveButtonClick() {
        val title = bookTitle.value
        val author = bookAuthor.value
        val pages = numberOfBookPages.value
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
                val id = CollectionBookId(arguments.collectionBookId ?: return@launch)
                editCollectionBook(id) { book ->
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
                val collectionBook = CollectionBook(
                    title = title,
                    author = author,
                    pagesNumber = pages,
                    readPages = readPages,
                    publicationDate = date,
                    isbn = isbn,
                    description = description
                )

                addCollectionBook(collectionBook)
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