package pl.kamilszustak.read.ui.main.book.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.book.EditBookUseCase
import pl.kamilszustak.read.domain.access.usecase.book.GetBookUseCase
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R

class ReadingProgressViewModel(
    private val arguments: ReadingProgressDialogFragmentArgs,
    private val getBook: GetBookUseCase,
    private val editBook: EditBookUseCase,
) : BaseViewModel<ReadingProgressEvent, ReadingProgressAction>() {

    private val _book: MutableLiveData<Book> = UniqueLiveData()
    val book: LiveData<Book> = _book

    val readPages: MutableLiveData<Int> = UniqueLiveData(0)

    init {
        val id = BookId(arguments.bookId)
        viewModelScope.launch(Dispatchers.Main) {
            _book.value = getBook(id).also { book ->
                if (book != null) {
                    readPages.value = book.readPages
                }
            }
        }
    }

    override fun handleEvent(event: ReadingProgressEvent) {
        when (event) {
            ReadingProgressEvent.OnCancelButtonClicked -> {
                _action.value = ReadingProgressAction.NavigateUp
            }

            ReadingProgressEvent.OnSaveButtonClicked -> {
                handleSaveButtonClicked()
            }
        }
    }

    private fun handleSaveButtonClicked() {
        val pages = readPages.value

        if (pages == null || pages < 0) {
            _action.value = ReadingProgressAction.Error(R.string.invalid_read_pages_number_error_message)
            return
        }

        val id = BookId(arguments.bookId)
        viewModelScope.launch(Dispatchers.Main) {
            editBook(id) { book -> book.copy(readPages = pages) }
                .onSuccess {
                    with(_action) {
                        value = ReadingProgressAction.ProgressUpdated
                        value = ReadingProgressAction.NavigateUp
                    }
                }
                .onFailure { _action.value = ReadingProgressAction.Error(R.string.reading_progress_edit_error_message) }
        }
    }
}