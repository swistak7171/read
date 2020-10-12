package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import pl.kamilszustak.read.domain.access.usecase.collection.GetAllCollectionBooksUseCase
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val getAllCollectionBooks: GetAllCollectionBooksUseCase,
) : BaseViewModel<CollectionEvent, CollectionState>() {

    val collectionBooks: LiveData<List<CollectionBook>> = getAllCollectionBooks()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: CollectionEvent) {
        when (event) {
            CollectionEvent.OnAddBookButtonClicked -> {
                _state.value = CollectionState.NavigateToBookEditFragment
            }
        }
    }
}