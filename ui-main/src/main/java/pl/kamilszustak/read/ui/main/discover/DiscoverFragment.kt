package pl.kamilszustak.read.ui.main.discover

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentDiscoverBinding
import javax.inject.Inject

class DiscoverFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>(R.layout.fragment_discover) {

    override val viewModel: DiscoverViewModel by viewModels(viewModelFactory)
    override val binding: FragmentDiscoverBinding by viewBinding(FragmentDiscoverBinding::bind)
}