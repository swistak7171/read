package pl.kamilszustak.read.ui.main.quotes

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.quote.DeleteQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.ObserveAllQuotesUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val observeAllQuotes: ObserveAllQuotesUseCase,
    private val deleteQuote: DeleteQuoteUseCase,
) : BaseViewModel<QuotesEvent, QuotesAction>() {

    val quotes: LiveData<List<Quote>> = observeAllQuotes()
        .asLiveData(viewModelScope.coroutineContext)

    override fun handleEvent(event: QuotesEvent) {
        when (event) {
            is QuotesEvent.OnAddQuoteButtonClicked -> {
                _action.value = QuotesAction.NavigateToQuoteEditFragment()
            }

            is QuotesEvent.OnShareQuoteButtonClicked -> {
                handleShareButtonClick(event)
            }

            is QuotesEvent.OnEditQuoteButtonClicked -> {
                _action.value = QuotesAction.NavigateToQuoteEditFragment(event.quoteId)
            }

            is QuotesEvent.OnDeleteQuoteButtonClicked -> {
                handleDeleteButtonClick(event)
            }
        }
    }

    private fun handleShareButtonClick(event: QuotesEvent.OnShareQuoteButtonClicked) {
        val quote = quotes.value?.find { it.id == event.quoteId } ?: return
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, quote.overview)
        }

        _action.value = QuotesAction.ShareQuote(intent, R.string.share_quote_chooser_title)
    }

    private fun handleDeleteButtonClick(event: QuotesEvent.OnDeleteQuoteButtonClicked) {
        viewModelScope.launch(Dispatchers.Main) {
            deleteQuote(event.quoteId)
                .onSuccess { _action.value = QuotesAction.QuoteDeleted }
                .onFailure { _action.value = QuotesAction.Error(R.string.deleting_quote_error_message) }
        }
    }
}