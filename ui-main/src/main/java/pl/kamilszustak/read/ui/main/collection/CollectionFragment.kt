package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.model.domain.CollectionBook
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.util.updateModels
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentCollectionBinding
import javax.inject.Inject

class CollectionFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentCollectionBinding, CollectionViewModel>(R.layout.fragment_collection) {

    override val viewModel: CollectionViewModel by viewModels(viewModelFactory)
    override val binding: FragmentCollectionBinding by viewBinding(FragmentCollectionBinding::bind)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<CollectionBook, CollectionBookItem> by lazy {
        ModelAdapter { CollectionBookItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            this.onLongClickListener = { view, adapter, item, position ->
                navigator.navigateToReadingProgressDialogFragment(item.model.id)
                true
            }
        }
        binding.booksRecyclerView.apply {
            adapter = fastAdapter
        }
    }

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
                    navigate(direction)
                }
            }
        }

        viewModel.collectionBooks.observe(viewLifecycleOwner) { books ->
            modelAdapter.updateModels(books)
        }
    }

    private inner class Navigator {
        fun navigateToReadingProgressDialogFragment(collectionBookId: CollectionBookId) {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToReadingProgressDialogFragment(collectionBookId.value)
            navigate(direction)
        }
    }
}