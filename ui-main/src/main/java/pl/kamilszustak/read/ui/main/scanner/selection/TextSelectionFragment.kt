package pl.kamilszustak.read.ui.main.scanner.selection

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.dialog
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_text_selection_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.textSelectionModeItem -> {
                viewModel.dispatchEvent(TextSelectionEvent.OnTextSelectionModeButtonClicked)
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

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is TextSelectionAction.ShowTextSelectionModeDialog -> {
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
            }
        }

        viewModel.imageBitmap.observe(viewLifecycleOwner) { bitmap ->
            if (bitmap != null) {
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }
}