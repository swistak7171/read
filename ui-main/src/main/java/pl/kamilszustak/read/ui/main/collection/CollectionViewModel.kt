package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.collection.DeleteCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetAllCollectionBooksUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val deleteCollectionBook: DeleteCollectionBookUseCase,
    private val getAllCollectionBooks: GetAllCollectionBooksUseCase,
) : BaseViewModel<CollectionEvent, CollectionState>() {

    val collectionBooks: LiveData<List<CollectionBook>> = getAllCollectionBooks()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: CollectionEvent) {
        when (event) {
            CollectionEvent.OnAddBookButtonClicked -> {
                _state.value = CollectionState.NavigateToBookEditFragment()
            }

            is CollectionEvent.OnBookLongClicked -> {
                _state.value = CollectionState.NavigateToReadingProgressDialogFragment(event.collectionBookId)
            }

            is CollectionEvent.OnUpdateReadingProgressButtonClicked -> {
                _state.value = CollectionState.NavigateToReadingProgressDialogFragment(event.collectionBookId)
            }

            is CollectionEvent.OnEditBookButtonClicked -> {
                _state.value = CollectionState.NavigateToBookEditFragment(event.collectionBookId)
            }

            is CollectionEvent.OnDeleteBookButtonClicked -> {
                handleDeleteBookButtonClick(event)
            }
        }
    }

    private fun handleDeleteBookButtonClick(event: CollectionEvent.OnDeleteBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteCollectionBook(event.collectionBookId)
                .onSuccess { _state.value = CollectionState.BookDeleted }
                .onFailure { _state.value = CollectionState.Error(R.string.deleting_book_error_message) }
        }
    }
}