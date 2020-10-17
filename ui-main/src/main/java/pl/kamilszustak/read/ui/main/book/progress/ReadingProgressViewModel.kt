package pl.kamilszustak.read.ui.main.book.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.UpdateCollectionBookUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel

class ReadingProgressViewModel(
    private val arguments: ReadingProgressDialogFragmentArgs,
    private val getCollectionBook: GetCollectionBookUseCase,
    private val updateCollectionBook: UpdateCollectionBookUseCase,
) : BaseViewModel<ReadingProgressEvent, ReadingProgressState>() {

    private val _collectionBook: UniqueLiveData<CollectionBook> = UniqueLiveData()
    val collectionBook: LiveData<CollectionBook> = _collectionBook

    val readPages: UniqueLiveData<Int> = UniqueLiveData()

    init {
        val id = CollectionBookId(arguments.collectionBookId)
        viewModelScope.launch(Dispatchers.Main) {
            _collectionBook.value = getCollectionBook(id)
        }
    }

    override fun handleEvent(event: ReadingProgressEvent) {
        when (event) {
            ReadingProgressEvent.OnCancelButtonClicked -> {
                _state.value = ReadingProgressState.NavigateUp
            }

            ReadingProgressEvent.OnSaveButtonClicked -> {
                handleSaveButtonClicked()
            }
        }
    }

    private fun handleSaveButtonClicked() {

    }
}