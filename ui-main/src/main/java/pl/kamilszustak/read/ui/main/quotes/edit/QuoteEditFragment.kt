package pl.kamilszustak.read.ui.main.quotes.edit

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuoteEditBinding
import javax.inject.Inject

class QuoteEditFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentQuoteEditBinding, QuoteEditViewModel>(R.layout.fragment_quote_edit) {

    override val viewModel: QuoteEditViewModel by viewModels(viewModelFactory)
}