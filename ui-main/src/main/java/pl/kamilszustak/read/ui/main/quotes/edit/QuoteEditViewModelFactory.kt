package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.EditQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteColorsUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetQuoteUseCase

class QuoteEditViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: QuoteEditFragmentArgs,
    private val getQuoteUseCase: GetQuoteUseCase,
    private val addQuoteUseCase: AddQuoteUseCase,
    private val editQuoteUseCase: EditQuoteUseCase,
    private val getQuoteColorsUseCase: GetQuoteColorsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        QuoteEditViewModel(
            arguments = arguments,
            getQuote = getQuoteUseCase,
            addQuote = addQuoteUseCase,
            editQuote = editQuoteUseCase,
            getQuoteColors = getQuoteColorsUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: QuoteEditFragmentArgs): QuoteEditViewModelFactory
    }
}