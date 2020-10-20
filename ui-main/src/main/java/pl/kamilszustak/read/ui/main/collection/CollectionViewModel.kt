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
    private val getAllCollectionBooks: GetAllCollectionBooksUseCase,
    private val deleteCollectionBook: DeleteCollectionBookUseCase,
) : BaseViewModel<CollectionEvent, CollectionAction>() {

    val collectionBooks: LiveData<List<CollectionBook>> = getAllCollectionBooks()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: CollectionEvent) {
        when (event) {
            CollectionEvent.OnAddBookButtonClicked -> {
                _action.value = CollectionAction.ShowAddBookDialog(R.array.add_book_dialog_options)
            }

            is CollectionEvent.OnDialogOptionSelected -> {
                when (event.index) {
                    0 -> _action.value = CollectionAction.NavigateToBookEditFragment()
                    1 -> _action.value = CollectionAction.NavigateToSearchFragment
                }
            }

            is CollectionEvent.OnBookLongClicked -> {
                _action.value = CollectionAction.NavigateToReadingProgressDialogFragment(event.collectionBookId)
            }

            is CollectionEvent.OnUpdateReadingProgressButtonClicked -> {
                _action.value = CollectionAction.NavigateToReadingProgressDialogFragment(event.collectionBookId)
            }

            is CollectionEvent.OnEditBookButtonClicked -> {
                _action.value = CollectionAction.NavigateToBookEditFragment(event.collectionBookId)
            }

            is CollectionEvent.OnDeleteBookButtonClicked -> {
                handleDeleteBookButtonClick(event)
            }
        }
    }

    private fun handleDeleteBookButtonClick(event: CollectionEvent.OnDeleteBookButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteCollectionBook(event.collectionBookId)
                .onSuccess { _action.value = CollectionAction.BookDeleted }
                .onFailure { _action.value = CollectionAction.Error(R.string.deleting_book_error_message) }
        }
    }
}