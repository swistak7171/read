package pl.kamilszustak.read.ui.main.book.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.EditCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R

class ReadingProgressViewModel(
    private val arguments: ReadingProgressDialogFragmentArgs,
    private val getCollectionBook: GetCollectionBookUseCase,
    private val editCollectionBook: EditCollectionBookUseCase,
) : BaseViewModel<ReadingProgressEvent, ReadingProgressAction>() {

    private val _collectionBook: UniqueLiveData<CollectionBook> = UniqueLiveData()
    val collectionBook: LiveData<CollectionBook> = _collectionBook

    val readPages: UniqueLiveData<Int> = UniqueLiveData(0)

    init {
        val id = CollectionBookId(arguments.collectionBookId)
        viewModelScope.launch(Dispatchers.Main) {
            _collectionBook.value = getCollectionBook(id).also { book ->
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

        val id = CollectionBookId(arguments.collectionBookId)
        viewModelScope.launch(Dispatchers.Main) {
            editCollectionBook(id) { book -> book.copy(readPages = pages) }
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