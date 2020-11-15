package pl.kamilszustak.read.ui.main.scanner.selection

import android.os.Bundle
import android.util.Size
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentTextSelectionBinding
import javax.inject.Inject

class TextSelectionFragment @Inject constructor(
    viewModelFactory: TextSelectionViewModelFactory.Factory,
): BaseFragment<FragmentTextSelectionBinding, TextSelectionViewModel>(R.layout.fragment_text_selection) {

    override val viewModel: TextSelectionViewModel by viewModels { viewModelFactory.create(args) }
    override val binding: FragmentTextSelectionBinding by viewBinding(FragmentTextSelectionBinding::bind)
    private val args: TextSelectionFragmentArgs by navArgs()
    private val navigator: Navigator = Navigator()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_text_selection_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.textRecognitionItem -> {
                viewModel.dispatchEvent(TextSelectionEvent.OnTextRecognitionButtonClicked)
                true
            }

            R.id.textSelectionModeItem -> {
                viewModel.dispatchEvent(TextSelectionEvent.OnTextSelectionModeButtonClicked)
                true
            }

            R.id.restoreImageItem -> {
                viewModel.dispatchEvent(TextSelectionEvent.OnRestoreImageButtonClicked)
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

    override fun setListeners() {
        binding.imageView.setOnTouchListener { v, event ->
            val size = Size(binding.imageView.width, binding.imageView.height)
            val event = TextSelectionEvent.OnImageViewTouch(event, size)
            viewModel.dispatchEvent(event)
            true
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is TextSelectionAction.ShowTextSelectionModeDialog -> showTextSelectionModeDialog(action)
                TextSelectionAction.InvalidateImageView -> binding.imageView.invalidate()
                is TextSelectionAction.Error -> showErrorToast(action)
                is TextSelectionAction.NavigateToQuoteEditFragment -> navigator.navigateToQuoteEditFragment(action.content)
            }
        }

        viewModel.imageDrawable.observe(viewLifecycleOwner) { drawable ->
            if (drawable != null) {
                binding.imageView.setImageDrawable(drawable)
            }
        }
    }

    private fun showTextSelectionModeDialog(action: TextSelectionAction.ShowTextSelectionModeDialog) {
        val items = action.itemsResources.map { getString(it) }
        dialog {
            title(R.string.text_selection_mode)
            listItemsSingleChoice(
                items = items,
                initialSelection = action.initialSelection,
                waitForPositiveButton = false,
                selection = { dialog, index, text ->
                    val event = TextSelectionEvent.OnTextSelectionModeSelected(index)
                    viewModel.dispatchEvent(event)
                }
            )
        }
    }

    private fun showErrorToast(action: TextSelectionAction.Error) {
        when {
            action.messageResourceId != null -> errorToast(action.messageResourceId)
            action.throwable != null -> errorToast(action.throwable.message ?: "")
        }
    }

    private inner class Navigator {
        fun navigateToQuoteEditFragment(content: String) {
            val direction = TextSelectionFragmentDirections.actionTextSelectionFragmentToNavigationQuoteEdit(
                content = content
            )
            navigate(direction)
        }
    }
}