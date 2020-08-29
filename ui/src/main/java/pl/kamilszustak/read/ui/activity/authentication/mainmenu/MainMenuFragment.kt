package pl.kamilszustak.read.ui.activity.authentication.mainmenu

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.R
import pl.kamilszustak.read.ui.base.BaseFragment
import pl.kamilszustak.read.ui.binding.viewBinding
import pl.kamilszustak.read.ui.databinding.FragmentMainMenuBinding
import pl.kamilszustak.read.ui.util.viewModels
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentMainMenuBinding, MainMenuViewModel>(R.layout.fragment_main_menu) {

    override val binding: FragmentMainMenuBinding by viewBinding(FragmentMainMenuBinding::bind)
    override val viewModel: MainMenuViewModel by viewModels(viewModelFactory)
}