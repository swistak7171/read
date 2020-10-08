package pl.kamilszustak.read.ui.main.quotes

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuotesBinding
import javax.inject.Inject

class QuotesFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentQuotesBinding, QuotesViewModel>(R.layout.fragment_quotes) {

    override val viewModel: QuotesViewModel by viewModels(viewModelFactory)
    override val binding: FragmentQuotesBinding by viewBinding(FragmentQuotesBinding::bind)
}