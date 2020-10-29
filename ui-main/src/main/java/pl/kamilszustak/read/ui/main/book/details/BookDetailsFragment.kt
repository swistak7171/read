package pl.kamilszustak.read.ui.main.book.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentBookDetailsBinding
import javax.inject.Inject

class BookDetailsFragment @Inject constructor(
    private val viewModelFactory: BookDetailsViewModelFactory.Factory
) : BaseFragment<FragmentBookDetailsBinding, BookDetailsViewModel>(R.layout.fragment_book_details) {

    override val viewModel: BookDetailsViewModel by viewModels { viewModelFactory.create(args) }
    override val binding: FragmentBookDetailsBinding by viewBinding(FragmentBookDetailsBinding::bind)
    private val args: BookDetailsFragmentArgs by navArgs()

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->

        }

        viewModel.book.observe(viewLifecycleOwner) { book ->
            if (book == null) {
                return@observe
            }

            
        }
    }
}