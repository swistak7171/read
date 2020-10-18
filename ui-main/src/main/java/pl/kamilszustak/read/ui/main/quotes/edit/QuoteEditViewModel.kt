package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.EditQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import timber.log.Timber

class QuoteEditViewModel(
    private val arguments: QuoteEditFragmentArgs,
    private val getQuote: GetQuoteUseCase,
    private val addQuote: AddQuoteUseCase,
    private val editQuote: EditQuoteUseCase,
) : BaseViewModel<QuoteEditEvent, QuoteEditState>() {

    private val inEditMode: Boolean = (arguments.quoteId != null)

    private val _actionBarTitle: UniqueLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val quoteContent: UniqueLiveData<String> = UniqueLiveData()
    val quoteAuthor: UniqueLiveData<String> = UniqueLiveData()
    val quoteBook: UniqueLiveData<String> = UniqueLiveData()

    init {
        _actionBarTitle.value = R.string.add_quote

        if (inEditMode) {
            viewModelScope.launch(Dispatchers.Main) {
                val id = QuoteId(arguments.quoteId ?: return@launch)
                val quote = getQuote(id)
                if (quote != null) {
                    assignQuote(quote)
                }
            }
        }
    }

    override fun handleEvent(event: QuoteEditEvent) {
        when (event) {
            QuoteEditEvent.OnAddQuoteButtonClicked -> handleSaveButtonClick()
        }
    }

    private fun assignQuote(quote: Quote) {
        quoteContent.value = quote.content
        quoteAuthor.value = quote.author
        quoteBook.value = quote.book
    }

    private fun handleSaveButtonClick() {
        val content = quoteContent.value
        val author = quoteAuthor.value
        val book = quoteBook.value

        if (content.isNullOrBlank()) {
            _state.value = QuoteEditState.Error(R.string.blank_quote_content)
            return
        }

        if (author.isNullOrBlank()) {
            _state.value = QuoteEditState.Error(R.string.blank_quote_author)
            return
        }

        val quote = Quote(
            content = content,
            author = author,
            book = book
        )

        viewModelScope.launch(Dispatchers.Main) {
            addQuote(quote)
                .onSuccess {
                    _state.value = QuoteEditState.QuoteAdded
                }
                .onFailure {
                    Timber.e(it)
                    _state.value = QuoteEditState.Error(R.string.adding_quote_error_message)
                }
        }
    }
}