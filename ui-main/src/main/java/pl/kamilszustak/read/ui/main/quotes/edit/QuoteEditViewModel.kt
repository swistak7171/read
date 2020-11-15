package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.EditQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteColorsUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteUseCase
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R

class QuoteEditViewModel(
    private val arguments: QuoteEditFragmentArgs,
    private val getQuote: GetQuoteUseCase,
    private val addQuote: AddQuoteUseCase,
    private val editQuote: EditQuoteUseCase,
    private val getQuoteColors: GetQuoteColorsUseCase,
) : BaseViewModel<QuoteEditEvent, QuoteEditAction>() {

    private val inEditMode: Boolean = (arguments.quoteId != null)

    private val _actionBarTitle: MutableLiveData<Int> = UniqueLiveData()
    val actionBarTitle: LiveData<Int>
        get() = _actionBarTitle

    val quoteContent: MutableLiveData<String> = UniqueLiveData()
    val quoteAuthor: MutableLiveData<String> = UniqueLiveData()
    val quoteBook: MutableLiveData<String> = UniqueLiveData()

    val colors: List<Int> = getQuoteColors()

    private val _selectedColorIndex: MutableLiveData<Int> = UniqueLiveData(0)
    val selectedColorIndex: LiveData<Int>
        get() = _selectedColorIndex

    init {
        _actionBarTitle.value = R.string.add_quote

        if (arguments.content != null) {
            quoteContent.value = arguments.content
        }

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
            is QuoteEditEvent.OnColorSelected -> handleColorSelection(event)
            QuoteEditEvent.OnSaveQuoteButtonClicked-> handleSaveButtonClick()
        }
    }

    private fun assignQuote(quote: Quote) {
        quoteContent.value = quote.content
        quoteAuthor.value = quote.author
        quoteBook.value = quote.book
        _selectedColorIndex.value = colors.indexOf(quote.backgroundColorValue)
    }

    private fun handleColorSelection(event: QuoteEditEvent.OnColorSelected) {
        _selectedColorIndex.value = event.index
    }

    private fun handleSaveButtonClick() {
        val content = quoteContent.value
        val author = quoteAuthor.value
        val book = quoteBook.value
        val colorIndex = _selectedColorIndex.value
        val color = if (colorIndex != null && colorIndex >= 0) {
            colors.getOrNull(colorIndex)
        } else {
            null
        }

        if (content.isNullOrBlank()) {
            _action.value = QuoteEditAction.Error(R.string.blank_quote_content)
            return
        }

        if (author.isNullOrBlank()) {
            _action.value = QuoteEditAction.Error(R.string.blank_quote_author)
            return
        }

        if (color == null) {
            _action.value = QuoteEditAction.Error(R.string.quote_color_not_selected)
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            val result = if (inEditMode) {
                val id = QuoteId(arguments.quoteId ?: return@launch)
                editQuote(id) { quote ->
                    quote.copy(
                        content = content,
                        author = author,
                        book = book,
                        backgroundColorValue = color
                    )
                }
            } else {
                val quote = Quote(
                    content = content,
                    author = author,
                    book = book,
                    backgroundColorValue = color
                )

                addQuote(quote)
            }

            result.onSuccess {
                val resourceId = if (inEditMode) {
                    R.string.quote_edited_successfully
                } else {
                    R.string.quote_added_successfully
                }

                with(_action) {
                    value = QuoteEditAction.QuoteSaved(resourceId)
                    value = QuoteEditAction.NavigateUp
                }
            }.onFailure {
                val resourceId = if (inEditMode) {
                    R.string.editing_quote_error_message
                } else {
                    R.string.adding_quote_error_message
                }

                _action.value = QuoteEditAction.Error(resourceId)
            }
        }
    }
}