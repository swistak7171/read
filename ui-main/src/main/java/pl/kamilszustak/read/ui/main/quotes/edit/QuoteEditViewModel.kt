package pl.kamilszustak.read.ui.main.quotes.edit

import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class QuoteEditViewModel @Inject constructor(
) : BaseViewModel<QuoteEditEvent, QuoteEditState>() {

    val quoteContent: UniqueLiveData<String> = UniqueLiveData()
    val quoteAuthor: UniqueLiveData<String> = UniqueLiveData()
    val quoteBook: UniqueLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: QuoteEditEvent) {
        when (event) {
            QuoteEditEvent.OnAddQuoteButtonClicked -> addQuote()
        }
    }

    private fun addQuote() {
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
    }
}