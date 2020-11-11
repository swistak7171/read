package pl.kamilszustak.read.ui.main.quotes.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.zedlabs.pastelplaceholder.Pastel
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
            R.id.saveQuoteItem -> {
                viewModel.dispatchEvent(QuoteEditEvent.OnSaveQuoteButtonClicked)
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
        Pastel.getColorLight()
    }

    override fun observeViewModel() {
        viewModel.actionBarTitle.observe(viewLifecycleOwner) { titleResourceId ->
            titleResourceId.useOrNull { resourceId ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = getString(resourceId)
            }
        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is QuoteEditAction.QuoteSaved -> successToast(action.messageResourceId)
                is QuoteEditAction.Error -> errorToast(action.messageResourceId)
                QuoteEditAction.NavigateUp -> navigateUp()
            }
        }
    }
}