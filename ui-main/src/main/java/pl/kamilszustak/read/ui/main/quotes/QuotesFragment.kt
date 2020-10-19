package pl.kamilszustak.read.ui.main.quotes

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuotesBinding
import javax.inject.Inject

class QuotesFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentQuotesBinding, QuotesViewModel>(R.layout.fragment_quotes) {

    override val viewModel: QuotesViewModel by viewModels(viewModelFactory)
    override val binding: FragmentQuotesBinding by viewBinding(FragmentQuotesBinding::bind)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<Quote, QuoteItem> by lazy {
        ModelAdapter { QuoteItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            addEventHook(object : ClickEventHook<QuoteItem>() {
                override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                    return if (viewHolder is QuoteItem.ViewHolder) {
                        viewHolder.binding.menuButton
                    } else {
                        null
                    }
                }

                override fun onClick(
                    v: View,
                    position: Int,
                    fastAdapter: FastAdapter<QuoteItem>,
                    item: QuoteItem
                ) {
                    popupMenu(v, R.menu.popup_menu_quote_item) {
                        setForceShowIcon(true)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.editQuoteItem -> {
                                    val event = QuotesEvent.OnEditQuoteButtonClicked(item.model.id)
                                    viewModel.dispatchEvent(event)
                                    true
                                }

                                R.id.deleteQuoteItem -> {
                                    dialog {
                                        title(R.string.delete_quote_dialog_title)
                                        message(R.string.delete_quote_dialog_message)
                                        positiveButton(R.string.yes) {
                                            val event = QuotesEvent.OnDeleteQuoteButtonClicked(item.model.id)
                                            viewModel.dispatchEvent(event)
                                        }
                                        negativeButton(R.string.no) { dialog ->
                                            dialog.dismiss()
                                        }
                                    }
                                    true
                                }

                                else -> {
                                    false
                                }
                            }
                        }
                    }
                }
            })
        }

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
                is QuotesState.NavigateToQuoteEditFragment -> {
                    navigator.navigateToQuoteEditFragment(state.quoteId)
                }

                is QuotesState.QuoteDeleted -> {
                    successToast(R.string.quote_deleted)
                }

                is QuotesState.Error -> {
                    errorToast(state.messageResourceId)
                }
            }
        }

        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            modelAdapter.updateModels(quotes)
        }
    }

    private inner class Navigator {
        fun navigateToQuoteEditFragment(quoteId: QuoteId? = null) {
            val direction = QuotesFragmentDirections.actionQuotesFragmentToQuoteEditFragment(quoteId?.value)
            navigate(direction)
        }
    }
}