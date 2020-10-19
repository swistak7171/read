package pl.kamilszustak.read.ui.main.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.quote.DeleteQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetAllQuotesUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val getAllQuotes: GetAllQuotesUseCase,
    private val deleteQuote: DeleteQuoteUseCase,
) : BaseViewModel<QuotesEvent, QuotesState>() {

    val quotes: LiveData<List<Quote>> = getAllQuotes()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: QuotesEvent) {
        when (event) {
            QuotesEvent.OnAddQuoteButtonClicked -> {
                _state.value = QuotesState.NavigateToQuoteEditFragment()
            }

            is QuotesEvent.OnEditQuoteButtonClicked -> {
                _state.value = QuotesState.NavigateToQuoteEditFragment(event.quoteId)
            }

            is QuotesEvent.OnDeleteQuoteButtonClicked -> {
                handleDeleteButtonClick(event)
            }
        }
    }

    private fun handleDeleteButtonClick(event: QuotesEvent.OnDeleteQuoteButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteQuote(event.quoteId)
                .onSuccess { _state.value = QuotesState.QuoteDeleted }
                .onFailure { _state.value = QuotesState.Error(R.string.deleting_quote_error_message) }
        }
    }
}