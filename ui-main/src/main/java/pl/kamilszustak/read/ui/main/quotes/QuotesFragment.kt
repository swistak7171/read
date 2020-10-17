package pl.kamilszustak.read.ui.main.quotes

import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigateTo
import pl.kamilszustak.read.ui.base.util.updateModels
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
    private val modelAdapter: ModelAdapter<Quote, QuoteItem> by lazy {
        ModelAdapter { QuoteItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter)
        binding.quotesRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun setListeners() {
        binding.addQuoteButton.setOnClickListener {
            viewModel.dispatchEvent(QuotesEvent.OnAddQuoteButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                QuotesState.NavigateToQuoteEditFragment -> {
                    val direction = QuotesFragmentDirections.actionQuotesFragmentToQuoteEditFragment()
                    navigateTo(direction)
                }
            }
        }

        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            modelAdapter.updateModels(quotes)
        }
    }
}