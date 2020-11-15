package pl.kamilszustak.read.ui.main.quotes

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.LongClickEventHook
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import pl.kamilszustak.model.common.id.QuoteId
import pl.kamilszustak.read.model.domain.Quote
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuotesBinding
import javax.inject.Inject

class QuotesFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentQuotesBinding, QuotesViewModel>(R.layout.fragment_quotes) {

    override val viewModel: QuotesViewModel by viewModels(viewModelFactory)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<Quote, QuoteItem> by lazy {
        ModelAdapter { QuoteItem(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_quotes_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addQuoteItem -> {
                viewModel.dispatchEvent(QuotesEvent.OnAddQuoteButtonClicked)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            addEventHook(object : LongClickEventHook<QuoteItem>() {
                override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                    return if (viewHolder is QuoteItem.ViewHolder) {
                        viewHolder.binding.root
                    } else {
                        null
                    }
                }

                override fun onLongClick(
                    v: View,
                    position: Int,
                    fastAdapter: FastAdapter<QuoteItem>,
                    item: QuoteItem
                ): Boolean {
                    openQuoteItemPopupMenu(v, item.model)

                    return true
                }
            })
        }

        binding.quotesRecyclerView.apply {
            itemAnimator = FadeInAnimator()
            adapter = fastAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is QuotesAction.NavigateToQuoteEditFragment -> {
                    navigator.navigateToQuoteEditFragment(action.quoteId)
                }

                is QuotesAction.QuoteDeleted -> {
                    successToast(R.string.quote_deleted)
                }

                is QuotesAction.Error -> {
                    errorToast(action.messageResourceId)
                }
            }
        }

        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            binding.quotesRecyclerView.isVisible = quotes.isNotEmpty()
            binding.emptyQuotesListLayout.root.isVisible = quotes.isEmpty()
            modelAdapter.updateModels(quotes)
        }
    }

    private fun openQuoteItemPopupMenu(view: View, quote: Quote) {
        popupMenu(view, R.menu.popup_menu_quote_item) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setForceShowIcon(true)
            }

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.editQuoteItem -> {
                        val event = QuotesEvent.OnEditQuoteButtonClicked(quote.id)
                        viewModel.dispatchEvent(event)
                        true
                    }

                    R.id.deleteQuoteItem -> {
                        dialog {
                            title(R.string.delete_quote_dialog_title)
                            message(R.string.delete_quote_dialog_message)
                            positiveButton(R.string.yes) {
                                val event = QuotesEvent.OnDeleteQuoteButtonClicked(quote.id)
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

    private inner class Navigator {
        fun navigateToQuoteEditFragment(quoteId: QuoteId? = null) {
            val direction = QuotesFragmentDirections.actionQuotesFragmentToNavigationQuoteEdit(
                quoteId = quoteId?.value
            )
            navigate(direction)
        }
    }
}