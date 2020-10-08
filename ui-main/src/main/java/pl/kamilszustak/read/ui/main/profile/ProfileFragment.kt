package pl.kamilszustak.read.ui.main.profile

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel: ProfileViewModel by viewModels(viewModelFactory)
    override val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
}