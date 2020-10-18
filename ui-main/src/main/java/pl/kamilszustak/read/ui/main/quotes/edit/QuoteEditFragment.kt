package pl.kamilszustak.read.ui.main.quotes.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.successToast
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuoteEditBinding
import javax.inject.Inject

class QuoteEditFragment @Inject constructor(
    viewModelFactory: QuoteEditViewModelFactory.Factory,
) : MainDataBindingFragment<FragmentQuoteEditBinding, QuoteEditViewModel>(R.layout.fragment_quote_edit) {

    override val viewModel: QuoteEditViewModel by viewModels { viewModelFactory.create(args) }
    private val args: QuoteEditFragmentArgs by navArgs()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_quote_edit_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addQuoteItem -> {
                viewModel.dispatchEvent(QuoteEditEvent.OnAddQuoteButtonClicked)
                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        viewModel.actionBarTitle.observe(viewLifecycleOwner) { titleResourceId ->
            titleResourceId.useOrNull { resourceId ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = getString(resourceId)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is QuoteEditState.Error -> {
                    errorToast(state.messageResourceId)
                }

                QuoteEditState.QuoteAdded -> {
                    successToast(R.string.quote_added_successfully)
                    navigateUp()
                }
            }
        }
    }
}