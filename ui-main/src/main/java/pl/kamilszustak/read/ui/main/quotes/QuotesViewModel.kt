package pl.kamilszustak.read.ui.main.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import pl.kamilszustak.read.domain.access.usecase.quote.GetAllQuotesUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val getAllQuotes: GetAllQuotesUseCase,
) : BaseViewModel<QuotesEvent, QuotesState>() {

    val quotes: LiveData<List<Quote>> = getAllQuotes()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: QuotesEvent) {
        when (event) {
            QuotesEvent.OnAddQuoteButtonClicked -> {
                _state.value = QuotesState.NavigateToQuoteEditFragment
            }
        }
    }
}