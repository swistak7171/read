package pl.kamilszustak.read.ui.main.scanner.selection

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.read.ui.base.binding.viewBinding
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

    override fun observeViewModel() {
        viewModel.imageBitmap.observe(viewLifecycleOwner) { bitmap ->
            if (bitmap != null) {
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }
}