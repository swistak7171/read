package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigateTo
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentCollectionBinding
import timber.log.Timber
import javax.inject.Inject

class CollectionFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentCollectionBinding, CollectionViewModel>(R.layout.fragment_collection) {

    override val viewModel: CollectionViewModel by viewModels(viewModelFactory)
    override val binding: FragmentCollectionBinding by viewBinding(FragmentCollectionBinding::bind)

    override fun setListeners() {
        binding.addBookButton.setOnClickListener {
            viewModel.dispatchEvent(CollectionEvent.OnAddBookButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                CollectionState.NavigateToBookEditFragment -> {
                    val direction = CollectionFragmentDirections.actionCollectionFragmentToBookEditFragment()
                    navigateTo(direction)
                }
            }
        }

        viewModel.collectionBooks.observe(viewLifecycleOwner) { books ->
            Timber.i(books.toString())
        }
    }
}