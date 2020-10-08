package pl.kamilszustak.read.ui.main.collection

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
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
}