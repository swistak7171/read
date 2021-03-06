package pl.kamilszustak.read.ui.main.quotes.edit

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import org.jetbrains.anko.padding
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.successToast
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentQuoteEditBinding
import javax.inject.Inject
import kotlin.properties.Delegates


class QuoteEditFragment @Inject constructor(
    viewModelFactory: QuoteEditViewModelFactory.Factory,
) : MainDataBindingFragment<FragmentQuoteEditBinding, QuoteEditViewModel>(R.layout.fragment_quote_edit) {

    override val viewModel: QuoteEditViewModel by viewModels { viewModelFactory.create(args) }
    private val args: QuoteEditFragmentArgs by navArgs()
    private var densityScale: Float by Delegates.notNull()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_quote_edit_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveQuoteItem -> {
                viewModel.dispatch(QuoteEditEvent.OnSaveQuoteButtonClicked)
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
        densityScale = resources.displayMetrics.density
    }

    override fun setListeners() {
        // Add ability to scroll EditText content
        binding.contentEditText.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
            }

            false
        }

        binding.colorLayout.children.forEachIndexed { index, view ->
            (view as? ImageView)?.setOnClickListener {
                val event = QuoteEditEvent.OnColorSelected(index)
                viewModel.dispatch(event)
            }
        }
    }

    override fun observeViewModel() {
        viewModel.actionBarTitle.observe(viewLifecycleOwner) { titleResourceId ->
            titleResourceId?.let { resourceId ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = getString(resourceId)
            }
        }

        viewModel.selectedColorIndex.observe(viewLifecycleOwner) { index ->
            binding.colorLayout.children.filter { it is ImageView }
                .forEachIndexed { elementIndex, view ->
                    view.padding = if (elementIndex != index) {
                        0
                    } else {
                        ((2 * densityScale) + 0.5F).toInt()
                    }
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