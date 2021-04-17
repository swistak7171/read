package pl.kamilszustak.read.ui.main.collection.archive

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentArchiveBinding
import javax.inject.Inject

class ArchiveFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
): BaseFragment<FragmentArchiveBinding, ArchiveViewModel>(R.layout.fragment_archive) {

    override val viewModel: ArchiveViewModel by viewModels(viewModelFactory)
    override val binding: FragmentArchiveBinding by viewBinding(FragmentArchiveBinding::bind)
}