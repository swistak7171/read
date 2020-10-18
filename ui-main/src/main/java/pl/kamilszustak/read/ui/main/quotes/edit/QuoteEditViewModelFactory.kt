package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase

class QuoteEditViewModelFactory @AssistedInject constructor(
    @Assisted private val arguments: QuoteEditFragmentArgs,
    private val addQuoteUseCase: AddQuoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        QuoteEditViewModel(
            arguments = arguments,
            addQuote = addQuoteUseCase
        ) as T

    @AssistedInject.Factory
    interface Factory {
        fun create(arguments: QuoteEditFragmentArgs): QuoteEditViewModelFactory
    }
}